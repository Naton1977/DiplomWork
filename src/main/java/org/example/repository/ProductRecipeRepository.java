package org.example.repository;

import org.example.domain.entity.ProductRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRecipeRepository extends JpaRepository<ProductRecipe, Integer> {
}
