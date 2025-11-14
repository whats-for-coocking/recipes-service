package com.whatsforcoocking.recipeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsforcoocking.recipeservice.exception.PayloadSerializationException;
import com.whatsforcoocking.recipeservice.model.OutboxEvent;
import com.whatsforcoocking.recipeservice.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxService {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void saveEvent(String aggregateType, UUID aggregateId,
                          String eventType, Object payload) {
        var event = OutboxEvent.builder()
                .aggregateType(aggregateType)
                .aggregateId(aggregateId)
                .eventType(eventType)
                .payload(serializePayload(payload))
                .createdAt(LocalDateTime.now())
                .build();

        outboxRepository.save(event);
    }

    private String serializePayload(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new PayloadSerializationException("Failed to serialize payload for event: " + payload, e);
        }
    }
}
