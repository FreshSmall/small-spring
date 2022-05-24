package com.demo.framework.beans.factory.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.BeanFactory;
import com.demo.framework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表接口
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getBean(name);
    }

    public <T> T doGetBean(String name, final Object... args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T)bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
}
