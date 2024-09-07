package com.example.Ingress_lab.client;

import com.example.Ingress_lab.client.decoder.CustomErrorDecoder;
import com.example.Ingress_lab.model.client.CardResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "ms-card",
            url = "${client.urls.ms-card}",
            configuration = CustomErrorDecoder.class,
            path = "/internal")
public interface CardClient {

    @GetMapping("/v1/cards/{id}")
    CardResponseDto getCardById(@PathVariable Long id);
}
