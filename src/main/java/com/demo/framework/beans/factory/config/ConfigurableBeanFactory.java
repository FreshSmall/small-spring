package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.factory.HierarchicalBeanFactory;

/**
 * 可配置的Bean工厂接口
 *
 * @author: yinchao
 * @ClassName: ConfigurableBeanFactory
 * @Description: 提供配置Bean工厂的能力
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:32
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例作用域标识
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域标识
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
