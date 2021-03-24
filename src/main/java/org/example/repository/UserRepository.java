package org.example.repository;

import org.example.domain.entity.DiaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<DiaryUser, Integer> {
    @Query("from DiaryUser c where c.userLogin like :userLogin")
    DiaryUser userByLogin(@Param("userLogin") String userLogin);

}
