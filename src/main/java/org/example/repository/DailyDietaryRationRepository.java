package org.example.repository;

import org.example.domain.entity.DailyDietaryRation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyDietaryRationRepository extends JpaRepository<DailyDietaryRation, Integer> {
}
