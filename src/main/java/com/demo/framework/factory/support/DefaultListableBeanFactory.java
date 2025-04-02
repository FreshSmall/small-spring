package com.demo.framework.factory.support;

import com.demo.framework.BeansException;
import com.demo.framework.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultListableBeanFactory 是一个完整的 BeanFactory 实现，支持注册和获取 BeanDefinition。
 * 它实现了 BeanDefinitionRegistry 接口，允许在运行时注册新的 BeanDefinition。
 * 这个类是 Spring 框架中最常用的 BeanFactory 实现之一。
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named" + beanName + "is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
