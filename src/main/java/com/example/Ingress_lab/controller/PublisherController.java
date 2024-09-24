package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.queue.ChangeTourStatusDto;
import com.example.Ingress_lab.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PostMapping
    public void sendToQueue(@RequestBody ChangeTourStatusDto dto) {
        publisherService.sendToQueue(dto);
    }
}
