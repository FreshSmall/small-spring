package com.demo.framework.beans.factory.support;

import com.demo.framework.beans.factory.config.BeanDefinition;

/**
 *
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表注册BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
