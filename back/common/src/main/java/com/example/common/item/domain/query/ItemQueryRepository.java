package com.example.common.item.domain.query;

import com.example.common.item.domain.Category;
import com.example.common.item.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemQueryRepository {

    @PersistenceContext
    private final EntityManager em;

    public ItemQueryRepository(EntityManager em) {
        this.em = em;
    }

    public List<Item> findItemsPage(int offset, int limit) {
        return em.createQuery("select i from Item i" +
                " join fetch i.category c" +
                " join fetch i.reputation r", Item.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Item> findItemsPageByCategoryInSubCategories(List<Category> subCategories, int offset, int limit) {
        return em.createQuery("select i from Item i" +
                " join fetch i.category" +
                " join fetch i.reputation" +
                " where i.category in :subCategories", Item.class)
                .setParameter("subCategories", subCategories)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
