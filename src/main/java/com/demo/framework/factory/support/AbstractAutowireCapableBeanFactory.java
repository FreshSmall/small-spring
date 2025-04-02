package com.demo.framework.factory.support;

import com.demo.framework.BeansException;
import com.demo.framework.factory.config.BeanDefinition;

/**
 * 初始化Bean的抽象类，并将类放着进单例池
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
