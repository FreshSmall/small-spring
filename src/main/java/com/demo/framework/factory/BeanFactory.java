package com.demo.framework.factory;

import com.demo.framework.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {
    Object getBean(String beanName);
}
