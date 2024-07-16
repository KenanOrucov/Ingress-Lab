package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.enums.LessonStatus;
import com.example.Ingress_lab.model.request.LessonRequest;
import com.example.Ingress_lab.model.response.LessonResponse;
import com.example.Ingress_lab.service.abstraction.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonResponse> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createLesson(@RequestBody LessonRequest request) {
        lessonService.createLesson(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateLesson(@PathVariable Long id, @RequestBody LessonRequest request) {
        lessonService.updateLesson(id, request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateLessonStatus(@PathVariable Long id, @RequestParam LessonStatus status) {
        lessonService.updateLessonStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
    }
}
