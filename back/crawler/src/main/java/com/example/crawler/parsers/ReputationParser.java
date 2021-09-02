package com.example.crawler.parsers;

import com.example.common.item.domain.cosmetic.review.Reputation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReputationParser<T> implements Parser {

    private Reputation reputation;

    public T parse(Document html){
        // 전체 평점
        Elements numbers = html.select("span[class^=totalArea_num]");
        if(numbers == null || numbers.size() < 2)
            return (T)reputation;
        StringBuffer totalStarRatio = new StringBuffer();
        for (int i = 0; i < 2; i++){
            totalStarRatio.append(numbers.get(i).html());
        }
        totalStarRatio.insert(1,".");
        BigDecimal bdTotalStarRatio = new BigDecimal(totalStarRatio.toString());

        // 전체 리뷰 수
        String totalReviewCount = "";
        for (int i = 2, size = numbers.size(); i < size; i++){
            totalReviewCount += numbers.get(i).html();
        }
        totalReviewCount = totalReviewCount.equals("") ? "0" : totalReviewCount;

        // 평점 비율
        Elements ratingRatios = html.select("span[class^=totalArea_bar]");

        List<BigDecimal> ratingRatioList = new ArrayList<>();
        String tempRtingRatio;
        for (Element ratingRatio : ratingRatios){
            tempRtingRatio = ratingRatio.html();
            BigDecimal bdRatingRatio = new BigDecimal(tempRtingRatio.substring(0,tempRtingRatio.length()-1));
            ratingRatioList.add(bdRatingRatio);
        }

        return (T)Reputation.builder()
                .totalStarRatio(bdTotalStarRatio)
                .totalReviewCount(Integer.parseInt(totalReviewCount))
                .fiveStarRatio(ratingRatioList.get(0))
                .fourStarRatio(ratingRatioList.get(1))
                .threeStarRatio(ratingRatioList.get(2))
                .twoStarRatio(ratingRatioList.get(3))
                .oneStarRatio(ratingRatioList.get(4))
                .build();
    }
}