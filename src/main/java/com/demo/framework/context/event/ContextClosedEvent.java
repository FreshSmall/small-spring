package com.demo.framework.context.event;

import com.demo.framework.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent {
    
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
    

}
