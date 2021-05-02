package com.arc.udemo.messaging;

import com.arc.udemo.domain.events.APIEvent;
import com.arc.udemo.repository.APIUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class APIEventListener {

    private APIUsageRepository apiCallEventRepository;

    @Autowired
    public APIEventListener(APIUsageRepository apiCallEventRepository) {
        this.apiCallEventRepository = apiCallEventRepository;
    }

    @Async
    @EventListener
    public void processAPICallEvent(APIEvent apiEvent){
        System.out.println("API Calls getting pushed:  " + apiEvent.toString());
        apiCallEventRepository.save(apiEvent);
    }
}
