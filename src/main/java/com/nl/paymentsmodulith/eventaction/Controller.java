package com.nl.paymentsmodulith.eventaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nl.paymentsmodulith.eventaction.action.Action;
import com.nl.paymentsmodulith.eventaction.action.RepublishUncompletedEvent;
import lombok.RequiredArgsConstructor;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "event-action")
public class Controller {

    private final RepublishUncompletedEvent republishUncompletedEvent;

    @GetMapping
    public ResponseEntity<String> publishEvent(@RequestParam(name = "action") String action) {
        republishUncompletedEvent.republish(Action.getActionByName(action));
        return new ResponseEntity<>("Event Triggered", HttpStatus.NO_CONTENT);
    }
}
