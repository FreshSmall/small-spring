package com.demo.framework.factory.support;

import com.demo.framework.factory.config.BeanDefinition;

/**
 *
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表注册BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
