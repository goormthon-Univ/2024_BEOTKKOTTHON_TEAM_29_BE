package com.goormthon.tomado.domain.task.repository;

import com.goormthon.tomado.domain.task.entity.Task;
import com.goormthon.tomado.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user = :user AND DATE(t.createdAt) = :date")
    List<Task> findByUserAndDate(@Param("user") User user, @Param("date") LocalDate date);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND MONTH(t.createdAt) = :month")
    List<Task> findByUserAndMonth(@Param("userId") Long userId, @Param("month") int month);
}
