package com.nl.paymentsmodulith.eventaction.action;

import java.util.Arrays;

import com.nl.paymentsmodulith.exception.ModulithException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {

    @Override
    public String convertToDatabaseColumn(Action action) {
        if(action == null) throw new ModulithException("action is null");
        return action.getCode();
    }

    @Override
    public Action convertToEntityAttribute(String code) {
        if(code == null) throw new RuntimeException("code is null");
        return Arrays.stream(Action.values())
                .filter(action -> action.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new ModulithException("action not found"));
    }
}
