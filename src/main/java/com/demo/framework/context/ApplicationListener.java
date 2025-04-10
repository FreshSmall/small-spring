package com.demo.framework.context;

import java.util.EventListener;

/**
 * 应用事件监听器接口
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 处理应用事件
     * @param event 要响应的事件
     */
    void onApplicationEvent(E event);
} 