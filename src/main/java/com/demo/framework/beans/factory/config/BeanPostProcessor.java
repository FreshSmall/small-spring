package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: BeanPostProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 22:58
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
