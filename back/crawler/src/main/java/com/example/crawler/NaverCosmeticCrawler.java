package com.example.crawler;

import com.example.common.item.domain.Category;
import com.example.common.item.domain.cosmetic.Cosmetic;
import com.example.common.item.domain.cosmetic.CosmeticIngredient;
import com.example.common.item.domain.cosmetic.ingredient.Feature;
import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import com.example.common.item.domain.cosmetic.review.Reputation;
import com.example.common.item.domain.cosmetic.review.Review;
import com.example.common.item.domain.vo.*;
import com.example.common.item.service.ItemService;
import com.example.crawler.constants.NaverFilterCondition;
import com.example.crawler.dto.CosmeticDto;
import com.example.crawler.dto.ItemDto;
import com.example.crawler.openApi.NaverOpenApi;
import com.example.crawler.parsers.*;
import com.example.crawler.util.JsoupUtil;
import com.example.crawler.util.NaverDataFilterUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NaverCosmeticCrawler {

    private final IngredientParser<Map<Ingredient,List<Feature>>> ingredientParser = new IngredientParser<>();
    private final DetailParser<CosmeticDto> detailParser = new DetailParser<>();
    private final ReviewParser<List<Review>> reviewParser = new ReviewParser<>();
    private final ReputationParser<Reputation> reputationParser = new ReputationParser<>();
    private final Map<String, ItemService> itemService;

    public NaverCosmeticCrawler( Map<String, ItemService> itemService) {
        this.itemService = itemService;
    }

    //매일 새벽 2시
    //테스트 데이터 수집-오후1시
    @Scheduled(cron = "0 0 13 * * *")
    public void callCrawling() {
        /**
         * ITEM > 스킨케어 > 스킨/토너, 로션, 에센스, 크림
         *          > 선케어 > 선크림, 선스프레이, 선스틱
         *          > 색조메이크업 > 립스틱
         */
        String[] searchWords = {"로션", "스킨/토너","크림","선스틱","립스틱", "에센스",  "선크림", "선스프레이"};
        //headerKey에 각자꺼 하나씩만 넣어서 테스트 하세요.
        List<String[]> headerKey = Arrays.asList(new String[] {"8e27h73XVyTLPU22a6vP" ,"H7934AkrC7"}
                ,new String[] {"hZYuasI_mUjPblkowRLg" ,"maV_VpDoMo"}
                , new String[] {"38YRSgJ2ymNns1KiXVKb" ,"6UCajUvtGz"}
                ,new String[] {"ZrpGYz7eD_3dceir0VDv" ,"hm_a7dWGgC"});
        int idx = 0;
        int headerKeySize = headerKey.size();
        for (int i = 1; i <= 1000; i += 10) {
            for (String searchWord : searchWords) {
                String queryParameter = "&display=10&start=";
                crawling(queryParameter + String.valueOf(i), searchWord
                        ,headerKey.get(idx%headerKeySize)[0],headerKey.get(idx%headerKeySize)[1]);
                idx++;
            }
        }
    }

    public void crawling(String queryParameter, String searchWord,String clientId,String clientSecret) {

        NaverOpenApi naverOpenApi = new NaverOpenApi();
        String responseBody = naverOpenApi.getData(clientId, clientSecret, queryParameter, searchWord);

        JsonMapper jsonMapper = new JsonMapper<List<ItemDto>>();
        List<ItemDto> list = null;
        try {
            list = jsonMapper.getDataByKey(responseBody, "items");
        } catch (JsonProcessingException e) {
        }

        List<ItemDto> filteredList = NaverDataFilterUtil.itemFilter(list, NaverFilterCondition.NAVER_MALL_NAME.getCode()
                , NaverFilterCondition.NAVER_CATEGORY.getCode());

        NaverParser naverParser = new NaverParser();
        List<ItemDto> naverItems = filteredList.stream().map(item -> {
            item.setLink(naverParser.getRedirectedLink(item));
            return item;
        }).collect(Collectors.toList());

        for (ItemDto itemDto : naverItems){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            save(itemDto);
        }

    }

    @Transactional
    public void save(ItemDto itemDto){
        Document doc = JsoupUtil.getDocByUrl(itemDto.getLink());
        if (doc == null) return;

        //리뷰 & 평점 저장
        Reputation reputation = (Reputation) itemService.get("reputationService").save(
                new ReputationReviewVO(reputationParser.parse(doc), reviewParser.parse(doc)));

        //상세정보 + item 기본 정보 + 카테고리 + 평점 추가
        CosmeticDto cosmeticDto = detailParser.parse(doc);
        Cosmetic cosmetic = cosmeticDto.toEntity(itemDto);
        Category category = (Category) itemService.get("categoryService")
                .save(new ItemCategoryVO(cosmeticDto.getCategories()));
        cosmetic.setCategory(category);
        cosmetic.setReputation(reputation);

        //성분, 특징 저장 및 성분-특징 리스트 생성
        List<Ingredient> ingredients = (List<Ingredient>) itemService.get("ingredientService")
                .save(new IngredientVO(ingredientParser.parse(doc)));

        //화장품-성분 리스트 생성
        List<CosmeticIngredient> cosmeticIngredients = (List<CosmeticIngredient>) itemService.get("cosmeticIngredientService")
                .save(new CosmeticIngredientVO(ingredients));

        //화장품 저장
        itemService.get("cosmeticService").save(new CosmeticVO(cosmetic, cosmeticIngredients));
    }
}
