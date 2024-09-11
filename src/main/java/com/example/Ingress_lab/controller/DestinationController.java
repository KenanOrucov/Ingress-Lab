package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.cache.DestinationCacheData;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
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
@RequestMapping("/v1/destinations")
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping
    public List<DestinationResponse> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public DestinationCacheData getDestinationById(@PathVariable Long id) {
        return destinationService.getDestinationById(id);
    }

    @GetMapping("/tours/{id}")
    public List<DestinationResponse> getDestinationsByTourId(@PathVariable Long id) {
        return destinationService.getDestinationsByTourId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createDestination(@RequestBody DestinationRequest request) {
        destinationService.createDestination(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateDestination(@PathVariable Long id, @RequestBody DestinationRequest request) {
        destinationService.updateDestination(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
    }
}
