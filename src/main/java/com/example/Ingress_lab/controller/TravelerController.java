package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.request.TravelerRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.model.response.TravelerResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
import com.example.Ingress_lab.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void createTraveler(@RequestBody TravelerRequest request) {
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
