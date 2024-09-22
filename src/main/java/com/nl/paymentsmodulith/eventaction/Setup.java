package com.nl.paymentsmodulith.eventaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;


import com.nl.paymentsmodulith.eventaction.action.Action;
import com.nl.paymentsmodulith.eventaction.action.CustomEventMarker;
import lombok.RequiredArgsConstructor;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */
@Component
@RequiredArgsConstructor
public class Setup implements ApplicationRunner {
    private final EventActionRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        
        Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.nl.paymentsmodulith");
        
        Map<String, String> eventActionMap = new HashMap<>();
        
        provider.addIncludeFilter(new AnnotationTypeFilter(CustomEventMarker.class));
        for(BeanDefinition bd : beanDefs) {
            if(bd instanceof AnnotatedBeanDefinition) {
                Map<String, Object> annotAttributeMap =  ((AnnotatedBeanDefinition) bd)
                    .getMetadata()
                    .getAnnotationAttributes(CustomEventMarker.class.getCanonicalName());
                
                eventActionMap.put(annotAttributeMap.get("eventAction").toString(), bd.getBeanClassName());
            }
        }
        List<EventAction> eventActionList = new ArrayList<>(eventActionMap.size());
        
        eventActionMap.forEach((k, v) -> {
            EventAction eventAction = new EventAction();
            Action action = Action.getActionByName(k);
            eventAction.setAction(action);
            eventAction.setEventCanonicalName(v);
            
            if(repository.getEventActionByAction(action).isEmpty())
                eventActionList.add(eventAction);
        });
        
        if(!eventActionList.isEmpty())
            repository.saveAll(eventActionList);
    }
}
