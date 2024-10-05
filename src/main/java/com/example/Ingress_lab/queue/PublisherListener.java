package com.example.Ingress_lab.queue;

import com.example.Ingress_lab.exception.QueueException;
import com.example.Ingress_lab.model.queue.ChangeTourStatusDto;
import com.example.Ingress_lab.service.abstraction.TourService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublisherListener {
    private final TourService tourService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.publisher-service.queue}")
    public void consume(String message){
        ChangeTourStatusDto data = null;
        try {
            data = objectMapper.readValue(message, ChangeTourStatusDto.class);
        } catch (JsonProcessingException e) {
            log.error("ActionLog.consume.error message invalid format: {}", message);
        }catch (Exception e){
            throw new QueueException();
        }
        tourService.updateTourStatus(data.getId(), data.getStatus());
    }

}
