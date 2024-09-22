package com.nl.paymentsmodulith.eventaction.action;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */

@AllArgsConstructor
@Getter
public enum Action {
    PAYMENT("P"), EMAIL("E"), COMPLETE_PAYMENT("C");

    private final String code;

    public static Action getActionByName(final String name){
        return Arrays.stream(Action.values())
                .filter(action -> action.name().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
