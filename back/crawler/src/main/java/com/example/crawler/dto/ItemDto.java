package com.example.crawler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String productId;
    /**
     * 가격비교 상품	1
     * 가격비교 비매칭 일반상품	2
     * 가격비교 매칭 일반상품	3
     *
     * @see "https://developers.naver.com/docs/search/shopping/"
     */
    private String productType;
    private String title;
    private String lprice;
    private String hprice;
    private String brand;
    private String maker;
    private String image;
    private String link;
    private String mallName;
    private String category1;
    private String category2;
    private String category3;
    private String category4;

    public Boolean isEmpty() {
        return this.productType.isEmpty() ||
                this.mallName.isEmpty() ||
                this.category1.isEmpty();
    }

    public Boolean isNormalProduct() {
        return "1".equals(this.productType) ||
                "2".equals(this.productType) ||
                "3".equals(this.productType);
    }
}
