package com.arc.udemo.service;

import com.arc.udemo.domain.events.APIEvent;

public interface EventService {
    public void processEvent(APIEvent apiEvent);
}
