package com.arc.udemo.messaging;

import com.arc.udemo.domain.products.APIUsage;
import com.arc.udemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class APIUsagePublisher implements EventService, ApplicationEventPublisherAware {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void processEvent(final APIUsage apiUsage) {
        publisher.publishEvent(apiUsage);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}
