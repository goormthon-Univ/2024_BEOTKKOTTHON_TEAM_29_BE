package com.goormthon.tomado.domain.category.repository;

import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}
