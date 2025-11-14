package com.whatsforcoocking.recipeservice.repository;

import com.whatsforcoocking.recipeservice.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {
}
