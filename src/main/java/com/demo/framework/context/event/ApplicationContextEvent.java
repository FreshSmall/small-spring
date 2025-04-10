package com.demo.framework.context.event;

import com.demo.framework.context.ApplicationContext;
import com.demo.framework.context.ApplicationEvent;

/**
 * @author: yinchao
 * @ClassName: ApplicationContextEvent
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/10 22:34
 */
public class ApplicationContextEvent extends ApplicationEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
