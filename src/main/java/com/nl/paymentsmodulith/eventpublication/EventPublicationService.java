package com.nl.paymentsmodulith.eventpublication;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EventPublicationService {
    private final EventPublicationRepository repository;
    
    public EventPublicationService(final EventPublicationRepository repository) {
        this.repository = repository;
    }
    
    public List<CustomEventPublication> getUncompletedEvents() {
        return repository.getCustomEventPublicationByPublicationDateNotNullAndCompletionDateIsNull();}
}
