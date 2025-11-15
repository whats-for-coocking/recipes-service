package com.whatsforcoocking.recipeservice.worker;

import com.whatsforcoocking.recipeservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 30000)
    public void process(){
        var events = outboxRepository.findFirst100ByProcessedFalse();

        if (events.isEmpty()) return;

        for (var event : events){
            try {
                kafkaTemplate.send("recipes-topic", event.getAggregateId().toString(), event.getPayload());
                event.setProcessed(true);
                outboxRepository.save(event);
            } catch (Exception e){
                log.error("Failed to process outbox event: {}", event.getId(), e);
            }
        }
        log.info("[OutboxProcessor] processed {} events", events.size());
    }
}
