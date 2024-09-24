package com.example.Ingress_lab.service;

import com.example.Ingress_lab.queue.ChangeTourStatusDto;
import com.example.Ingress_lab.queue.QueueSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.Ingress_lab.model.enums.QueueName.PUBLISHER_Q;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final QueueSender<ChangeTourStatusDto> queueSender;

    public void sendToQueue(ChangeTourStatusDto dto) {
        queueSender.sendMessageToQ(String.valueOf(PUBLISHER_Q), dto);
    }
}
