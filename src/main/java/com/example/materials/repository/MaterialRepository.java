package com.example.materials.repository;

import com.example.materials.module.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Material> findByCourseId(Long courseId);
}