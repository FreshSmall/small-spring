package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.BeanFactory;

/**
 * 自动装配能力的Bean工厂接口
 *
 * @author: yinchao
 * @ClassName: AutowireCapableBeanFactory
 * @Description: 提供自动装配Bean的能力
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:34
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 的 postProcessAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
