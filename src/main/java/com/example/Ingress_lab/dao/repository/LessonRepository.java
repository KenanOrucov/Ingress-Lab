package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository  extends JpaRepository<LessonEntity, Long> {
}
