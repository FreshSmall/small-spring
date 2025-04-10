package com.demo.framework.context;

import java.util.EventObject;

/**
 * @author: yinchao
 * @ClassName: ApplicationEvent
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/10 22:33
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
