package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.request.TourRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.model.response.TourResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
import com.example.Ingress_lab.service.abstraction.TourService;
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
@RequestMapping("/v1/tours")
public class TourController {
    private final TourService tourService;

    @GetMapping
    public List<TourResponse> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/{id}")
    public TourResponse getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }

    @GetMapping("/travelers/{travelerId}")
    public List<TourResponse> getToursByTravelerId(@PathVariable Long travelerId) {
        return tourService.getToursByTravelerId(travelerId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createTour(@RequestBody TourRequest request) {
        tourService.createTour(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateTour(@PathVariable Long id, @RequestBody TourRequest request) {
        tourService.updateTour(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }
}
