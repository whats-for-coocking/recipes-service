package com.whatsforcoocking.recipeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {

    @Id
    @GeneratedValue
    private UUID id;

    private String aggregateType;
    private UUID aggregateId;
    private String eventType;

    @Column(columnDefinition = "jsonb")
    private String payload;

    private LocalDateTime createdAt;
    private boolean processed;
}
