package com.jhlab.ninety.domain.habits.repository;

import com.jhlab.ninety.domain.habits.entity.Habits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HabitsRepository extends JpaRepository<Habits, Long> {
    @Query("SELECT h FROM Habits h " +
            "JOIN FETCH h.user " +
            "LEFT JOIN FETCH h.repeatDays " +
            "WHERE h.id = :id")
    Optional<Habits> findByIdWithUserAndRepeatDays(@Param("id") Long id);

    @Query(value = "SELECT h FROM Habits h JOIN FETCH h.user u WHERE u.email = :userEmail",
            countQuery = "SELECT COUNT(h) FROM Habits h WHERE h.user.email = :userEmail")
    Page<Habits> findAllByUser(@Param("userEmail") String userEmail, Pageable pageable);
}
