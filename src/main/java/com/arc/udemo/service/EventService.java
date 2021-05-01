package com.arc.udemo.service;

import com.arc.udemo.domain.events.APICallEvent;

public interface EventService {
    public void processEvent(APICallEvent apiCallEvent);
}
