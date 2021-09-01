package com.example.common.item.domain.cosmetic.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository <Feature,Long> {

    Feature findByName(String name);
}
