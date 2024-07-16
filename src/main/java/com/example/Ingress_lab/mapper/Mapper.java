package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.LessonEntity;
import com.example.Ingress_lab.model.enums.LessonStatus;
import com.example.Ingress_lab.model.request.LessonRequest;
import com.example.Ingress_lab.model.response.LessonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public LessonEntity toLessonEntity(LessonRequest request) {
        if (request == null) {
            return null;
        }

        return LessonEntity.builder()
                .name(request.getName())
                .hours(request.getHours())
                .studentCount(request.getStudentCount())
                .status(LessonStatus.NEW)
                .build();
    }

    public LessonResponse toLessonResponse(LessonEntity entity) {
        if (entity == null) {
            return null;
        }

        return LessonResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .hours(entity.getHours())
                .studentCount(entity.getStudentCount())
                .status(entity.getStatus())
                .build();
    }

    public List<LessonResponse> toLessonResponses(List<LessonEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toLessonResponse)
                .toList();
    }

    public LessonEntity updateLesson(LessonEntity entity, LessonRequest request) {
        if (request == null || entity == null) {
            return null;
        }
        entity.setName(request.getName());
        entity.setHours(request.getHours());
        entity.setStudentCount(request.getStudentCount());
        return entity;
    }

    public LessonEntity updateLessonStatus(LessonEntity entity, LessonStatus status) {
        if (entity == null) {
            return null;
        }
        entity.setStatus(status);
        return entity;
    }
}
