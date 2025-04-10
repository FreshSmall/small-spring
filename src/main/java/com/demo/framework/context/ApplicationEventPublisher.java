package com.demo.framework.context;

public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event 事件
     */
    void publishEvent(ApplicationEvent event);
}
