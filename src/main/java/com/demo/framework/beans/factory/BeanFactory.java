package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

/**
 * Bean工厂接口
 *
 * @author: yinchao
 * @ClassName: BeanFactory
 * @Description: 定义获取Bean对象的方法
 * @team wuhan operational dev.
 * @date: 2025/4/6 00:10
 */
public interface BeanFactory {

    /**
     * 根据名称获取Bean对象
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 根据名称和参数获取Bean对象
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据名称和类型获取Bean对象
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
