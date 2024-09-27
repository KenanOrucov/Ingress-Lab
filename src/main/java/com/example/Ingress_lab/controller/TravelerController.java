package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.TravelerRequest;
import com.example.Ingress_lab.model.response.TravelerResponse;
import com.example.Ingress_lab.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/travelers")
public class TravelerController {
    private final TravelerService travelerService;

    @GetMapping
    public List<TravelerResponse> getAllTravelers() {
        return travelerService.getAllTravelers();
    }

    @GetMapping("/{id}")
    public TravelerResponse getTravelerById(@PathVariable Long id) {
        return travelerService.getTravelerById(id);
    }

    @GetMapping("/tours/{tourId}")
    public List<TravelerResponse> getTravelersByTourId(@PathVariable Long tourId) {
        return travelerService.getTravelersByTourId(tourId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("@permissionService.checkPermission(#userId, 'Travel-Tours', 'ADMIN')")
    public void createTraveler(@RequestParam Long userId, @RequestBody TravelerRequest request) {
        travelerService.createTraveler(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateTraveler(@PathVariable Long id, @RequestBody TravelerRequest request) {
        travelerService.updateTraveler(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTraveler(@PathVariable Long id) {
        travelerService.deleteTraveler(id);
    }
}
