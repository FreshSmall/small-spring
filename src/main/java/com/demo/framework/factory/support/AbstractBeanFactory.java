package com.demo.framework.factory.support;

import com.demo.framework.BeansException;
import com.demo.framework.factory.BeanFactory;
import com.demo.framework.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory 是一个抽象类，提供了 BeanFactory 的基本实现
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
