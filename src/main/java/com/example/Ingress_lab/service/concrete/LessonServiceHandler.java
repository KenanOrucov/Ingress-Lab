package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.LessonEntity;
import com.example.Ingress_lab.dao.repository.LessonRepository;
import com.example.Ingress_lab.model.enums.LessonStatus;
import com.example.Ingress_lab.model.request.LessonRequest;
import com.example.Ingress_lab.model.response.LessonResponse;
import com.example.Ingress_lab.service.abstraction.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Ingress_lab.mapper.Mapper.LESSON_MAPPER;
import static com.example.Ingress_lab.model.enums.LessonStatus.DELETED;

@Service
@RequiredArgsConstructor
public class LessonServiceHandler implements LessonService {
    private final LessonRepository lessonRepository;

    @Override
    public void createLesson(LessonRequest request) {
        lessonRepository.save(LESSON_MAPPER.toLessonEntity(request));
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return LESSON_MAPPER.toLessonResponse(fetchLessonIfExist(id));
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return LESSON_MAPPER
                .toLessonResponses(lessonRepository.findAll());
    }

    @Override
    public void updateLesson(Long id, LessonRequest request) {
        lessonRepository.save(
                LESSON_MAPPER
                        .updateLesson(fetchLessonIfExist(id), request));
    }

    @Override
    public void updateLessonStatus(Long id, LessonStatus status) {
        var lesson = fetchLessonIfExist(id);
        lesson.setStatus(status);
        lessonRepository.save(lesson);
    }


    @Override
    public void deleteLessonById(Long id) {
        var lesson = fetchLessonIfExist(id);
        lesson.setStatus(DELETED);
        lessonRepository.save(lesson);
    }

    private LessonEntity fetchLessonIfExist(Long id) {
        return lessonRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
