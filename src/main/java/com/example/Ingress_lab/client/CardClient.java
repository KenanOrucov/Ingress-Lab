package com.example.Ingress_lab.client;

import com.example.Ingress_lab.model.client.CardResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "ms-card", url = "${ms-card.client.url}")
public interface CardClient {

    @GetMapping("/internal/v1/cards/{id}")
    CardResponseDto getCardById(@PathVariable Long id);
}
