package com.nl.paymentsmodulith.eventaction;

import com.nl.paymentsmodulith.eventaction.action.Action;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(indexes = {
    @Index(name = "actionIdx", columnList = "action")
})
public class EventAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2, nullable = false)
    private Action action;
    @Column(name = "event_can", nullable = false)
    private String eventCanonicalName;
}
