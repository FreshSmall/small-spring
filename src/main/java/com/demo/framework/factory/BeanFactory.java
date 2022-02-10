package com.demo.framework.factory;

import com.demo.framework.BeansException;
import com.demo.framework.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
