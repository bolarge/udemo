package com.arc.udemo.messaging;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EntityEvents<T> extends ApplicationEvent implements ResolvableTypeProvider {

    public EntityEvents(T entity) {
        super(entity);
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(getSource()));
    }

}
