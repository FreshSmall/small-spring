package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author: yinchao
 * @ClassName: BeanFactoryPostProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 22:56
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
