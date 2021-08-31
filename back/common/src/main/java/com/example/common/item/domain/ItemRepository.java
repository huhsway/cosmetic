package com.example.common.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i" +
            " left join fetch i.category" +
            " join fetch i.reputation")
    List<Item> findItems();
    List<Item> findTop100ByTitleContains(String title);
    List<Item> findItemsByCategoryId(Long categoryId);
    List<Item> findItemsByCategory(Category category);
    @Query(value = "select i from Item i" +
            " join fetch i.category" +
            " join fetch i.reputation " +
            " where i.category in :subCategories")
    List<Item> findItemsByCategoryInSubCategories(@Param("subCategories") List<Category> subCategories);
    Item findByProductId(String productId);
}
