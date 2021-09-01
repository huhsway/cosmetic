package com.example.common.item.domain.cosmetic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {
    Optional<Cosmetic> findById(Long itemId);
}
