package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.request.PassportRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.model.response.PassportResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
import com.example.Ingress_lab.service.abstraction.PassportService;
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
@RequestMapping("/v1/passports")
public class PassportController {
    private final PassportService passportService;

    @GetMapping
    public List<PassportResponse> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/{id}")
    public PassportResponse getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createPassport(@RequestBody PassportRequest request) {
        passportService.createPassport(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updatePassport(@PathVariable Long id, @RequestBody PassportRequest request) {
        passportService.updatePassport(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }
}
