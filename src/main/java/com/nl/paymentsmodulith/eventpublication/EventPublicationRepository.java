package com.nl.paymentsmodulith.eventpublication;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPublicationRepository extends JpaRepository<CustomEventPublication, UUID> {
    List<CustomEventPublication>
    getCustomEventPublicationByPublicationDateNotNullAndCompletionDateIsNull();
}
