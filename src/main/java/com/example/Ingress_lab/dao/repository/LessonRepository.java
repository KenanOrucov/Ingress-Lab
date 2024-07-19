package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.LessonEntity;
import com.example.Ingress_lab.model.enums.LessonStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository  extends CrudRepository<LessonEntity, Long> {
    @Override
    List<LessonEntity> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM lessons WHERE hours >= 2")
    List<LessonEntity> getGreaterThanTwo();

    @Query(nativeQuery = true, value = "SELECT * FROM lessons WHERE student_count =:studentCount")
    List<LessonEntity> getWithStudentCount(Long studentCount);

    @Query(value = "SELECT l FROM LessonEntity l WHERE l.status =:status")
    List<LessonEntity> getWithLessonStatus(LessonStatus status);


}
