package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: InstantiationAwareBeanPostProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 23:01
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
