package org.example.repository;

import org.example.domain.entity.DailyDietaryRation;
import org.example.domain.entity.DiaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyDietaryRationRepository extends JpaRepository<DailyDietaryRation, Integer> {

}
