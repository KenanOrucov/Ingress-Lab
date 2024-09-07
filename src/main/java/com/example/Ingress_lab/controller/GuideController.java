package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.GuideRequest;
import com.example.Ingress_lab.model.response.GuideResponse;
import com.example.Ingress_lab.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/guides")
public class GuideController {
    private final GuideService guideService;

    @GetMapping
    public List<GuideResponse> getAllGuides() {
        return guideService.getAllGuides();
    }

    @GetMapping("/{id}")
    public GuideResponse getGuideById(@PathVariable Long id) {
        return guideService.getGuideById(id);
    }

    @GetMapping("/tours/{tourId}")
    public List<GuideResponse> getGuidesByTourId(@PathVariable Long tourId) {
        return guideService.getGuidesByTourId(tourId);
    }
    @GetMapping("/available")
    public List<GuideResponse> getAvailableGuides(@RequestParam("date") LocalDate date) {
        return guideService.getAvailableGuides(date);
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public void createGuide(@RequestBody GuideRequest request) {
        guideService.createGuide(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateGuide(@PathVariable Long id, @RequestBody GuideRequest request) {
        guideService.updateGuide(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteGuide(@PathVariable Long id) {
        guideService.deleteGuide(id);
    }
}
