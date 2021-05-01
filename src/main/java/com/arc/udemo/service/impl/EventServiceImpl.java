package com.arc.udemo.service.impl;

import com.arc.udemo.domain.events.APICallEvent;
import com.arc.udemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class EventServiceImpl implements EventService, ApplicationEventPublisherAware {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void processEvent(APICallEvent apiCallEvent) {
        publisher.publishEvent(apiCallEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}
