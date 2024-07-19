package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.LessonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository  extends CrudRepository<LessonEntity, Long> {

    @Override
    List<LessonEntity> findAll();
}
