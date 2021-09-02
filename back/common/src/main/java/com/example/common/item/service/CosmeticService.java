package com.example.common.item.service;

import com.example.common.item.api.dto.CosmeticResponseDto;
import com.example.common.item.domain.Item;
import com.example.common.item.domain.ItemRepository;
import com.example.common.item.domain.cosmetic.Cosmetic;
import com.example.common.item.domain.cosmetic.CosmeticIngredient;
import com.example.common.item.domain.cosmetic.CosmeticRepository;
import com.example.common.item.domain.vo.CosmeticVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.common.item.api.dto.CosmeticResponseDto.from;

@Service
@RequiredArgsConstructor
public class CosmeticService implements ItemService<Cosmetic, CosmeticVO>{

    private final ItemRepository itemRepository;
    private final CosmeticRepository cosmeticRepository;

    @Transactional
    @Override
    public Cosmetic save(CosmeticVO cosmeticVO){
        Cosmetic cosmetic = cosmeticVO.getCosmetic();
        List<CosmeticIngredient> cosmeticIngredients = cosmeticVO.getCosmeticIngredients();

        Item findItem = itemRepository.findByProductId(cosmetic.getProductId());
        //등록된 화장품의 경우, 성분 Update
        if (findItem != null) {
            addAllCosmeticIngredient((Cosmetic) findItem, cosmeticIngredients);
            return (Cosmetic) findItem;
        }
        addCosmeticIngredient(cosmetic, cosmeticIngredients);
        return itemRepository.save(cosmetic);
    }

    private void addAllCosmeticIngredient(Cosmetic cosmetic, List<CosmeticIngredient> cosmeticIngredients) {
        cosmetic.getCosmeticIngredients().addAll(cosmeticIngredients);
    }

    private void addCosmeticIngredient(Cosmetic cosmetic, List<CosmeticIngredient> cosmeticIngredients) {
        for (CosmeticIngredient cosmeticIngredient : cosmeticIngredients){
            cosmetic.addCosmeticIngredient(cosmeticIngredient);
        }
    }

    @Transactional(readOnly = true)
    public List<CosmeticResponseDto> findCosmetics() {
        return cosmeticRepository.findAll()
                .stream()
                .map(CosmeticResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CosmeticResponseDto findCosmeticByItemId(Long itemId) {
        Optional<Cosmetic> findCosmetic = cosmeticRepository.findById(itemId);
        if (findCosmetic.isEmpty()) {
            throw new NoSuchElementException("존재하지 않는 화장품입니다.");
        }
        return from(findCosmetic.get());
    }
}
