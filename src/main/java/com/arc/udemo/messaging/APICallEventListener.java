package com.arc.udemo.messaging;

import com.arc.udemo.domain.events.APICallEvent;
import com.arc.udemo.repository.APICallEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class APICallEventListener {

    private APICallEventRepository apiCallEventRepository;

    @Autowired
    public APICallEventListener(APICallEventRepository apiCallEventRepository) {
        this.apiCallEventRepository = apiCallEventRepository;
    }

    @Async
    @EventListener
    public void processAPICallEvent(APICallEvent apiCallEvent){
        //persist to data store
        System.out.println("API Calls getting pushed:  " + apiCallEvent.toString());
        apiCallEventRepository.save(apiCallEvent);
    }
}
