package com.example.Ingress_lab.scheduler;

import com.example.Ingress_lab.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardScheduler {
    private final CardService cardService;

    @Scheduled(cron = "0 0 3 * * *")
    @SchedulerLock(name = "updateCardAmount", lockAtLeastForString = "PT1M", lockAtMostForString = "PT3M")
    public void updateCardAmount() {
        cardService.updateCardAmount();
    }
}
