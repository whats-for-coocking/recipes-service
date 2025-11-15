package com.whatsforcoocking.recipeservice.repository;

import com.whatsforcoocking.recipeservice.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {

    List<OutboxEvent> findFirst100ByProcessedFalse();
}
