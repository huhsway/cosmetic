package com.example.common.item.api.dto;

import com.example.common.item.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {
    private Long id;
    private String productId;
    private String title;
    private String lPrice;
    private String hPrice;
    private String brand;
    private String maker;
    private String image;
    private String link;
    private CategoryResponseDto category;
    private ReputationResponseDto reputation;

    private ItemResponseDto(Item item) {
        this.id = item.getId();
        this.productId = item.getProductId();
        this.title = item.getTitle();
        this.lPrice = item.getLprice();
        this.hPrice = item.getHprice();
        this.brand = item.getBrand();
        this.maker = item.getMaker();
        this.image = item.getImage();
        this.link = item.getLink();
        this.category = CategoryResponseDto.from(item.getCategory());
        this.reputation = ReputationResponseDto.from(item.getReputation());
    }

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(item);
    }
}
