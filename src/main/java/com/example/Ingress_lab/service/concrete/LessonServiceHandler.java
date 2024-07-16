package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.repository.LessonRepository;
import com.example.Ingress_lab.mapper.Mapper;
import com.example.Ingress_lab.model.enums.LessonStatus;
import com.example.Ingress_lab.model.request.LessonRequest;
import com.example.Ingress_lab.model.response.LessonResponse;
import com.example.Ingress_lab.service.abstraction.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceHandler implements LessonService {
    private final Mapper mapper;
    private final LessonRepository lessonRepository;

    @Override
    public void createLesson(LessonRequest request) {
        lessonRepository.save(mapper.toLessonEntity(request));
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return lessonRepository
                .findById(id)
                .map(mapper::toLessonResponse)
                .orElse(null);
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return mapper
                .toLessonResponses(lessonRepository.findAll());
    }

    @Override
    public void updateLesson(Long id, LessonRequest request) {
        lessonRepository
                .save(mapper
                    .updateLesson(lessonRepository.findById(id).orElse(null),
                                        request));
    }

    @Override
    public void updateLessonStatus(Long id, LessonStatus status) {
        lessonRepository
                .save(
                        mapper.updateLessonStatus(lessonRepository.findById(id).orElse(null),
                                    status));
    }


    @Override
    public void deleteLessonById(Long id) {
        lessonRepository
                .deleteById(id);
    }
}
