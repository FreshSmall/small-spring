package com.demo.framework.context.event;

import com.demo.framework.beans.factory.BeanFactory;
import com.demo.framework.context.ApplicationEvent;
import com.demo.framework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}