package com.arc.udemo.service;

import com.arc.udemo.domain.products.APIUsage;

public interface EventService {
    public void processEvent(APIUsage apiUsage);
}
