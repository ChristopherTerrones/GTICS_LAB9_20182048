package com.example.gtics_lab9_20182048.repository;

import com.example.gtics_lab9_20182048.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByMealId(Integer mealId);
}
