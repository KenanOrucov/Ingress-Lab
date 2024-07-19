package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.enums.LessonStatus;
import com.example.Ingress_lab.model.request.LessonRequest;
import com.example.Ingress_lab.model.response.LessonResponse;

import java.util.List;

public interface LessonService {
    void createLesson(LessonRequest request);
    LessonResponse getLessonById(Long id);
    List<LessonResponse> getAllLessons();
    void updateLesson(Long id, LessonRequest request);
    void updateLessonStatus(Long id, LessonStatus status);
    void deleteLessonById(Long id);
}
