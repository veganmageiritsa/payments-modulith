package com.nl.paymentsmodulith.eventaction;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nl.paymentsmodulith.eventaction.action.Action;


/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */

@Repository
public interface EventActionRepository extends CrudRepository<EventAction, Long> {
    Optional<EventAction> getEventActionByAction(Action action);
}
