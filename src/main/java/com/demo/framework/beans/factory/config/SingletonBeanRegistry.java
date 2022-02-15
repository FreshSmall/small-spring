package com.demo.framework.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
