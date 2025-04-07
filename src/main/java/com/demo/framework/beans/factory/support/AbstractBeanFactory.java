package com.demo.framework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.BeanFactory;
import com.demo.framework.beans.factory.config.BeanDefinition;

/**
 * 抽象的Bean工厂
 *
 * @author: yinchao
 * @ClassName: AbstractBeanFactory
 * @Description: 实现BeanFactory接口，提供获取Bean对象的方法
 * @team wuhan operational dev.
 * @date: 2025/4/6 00:15
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
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 获取Bean对象
     *
     * @param name
     * @param args
     * @return
     */
    protected Object doGetBean(String name, final Object... args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    public ClassLoader getBeanClassLoader() {
        return ClassUtil.getClassLoader();
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
}
