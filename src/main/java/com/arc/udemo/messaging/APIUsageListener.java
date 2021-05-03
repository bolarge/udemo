package com.arc.udemo.messaging;

import com.arc.udemo.domain.products.APIUsage;
import com.arc.udemo.repository.APIUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class APIUsageListener {

    private APIUsageRepository apiCallEventRepository;

    @Autowired
    public APIUsageListener(APIUsageRepository apiCallEventRepository) {
        this.apiCallEventRepository = apiCallEventRepository;
    }

    @Async
    @EventListener
    protected void processAPICallEvent(APIUsage apiUsage){
        apiCallEventRepository.save(apiUsage);
    }
}
