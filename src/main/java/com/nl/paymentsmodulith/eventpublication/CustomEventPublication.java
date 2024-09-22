package com.nl.paymentsmodulith.eventpublication;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_publication")
public class CustomEventPublication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String listenerId;
  
    private String eventType;
    
    private String serializedEvent;
   
    private Timestamp publicationDate;
   
    private Timestamp completionDate;
    
}