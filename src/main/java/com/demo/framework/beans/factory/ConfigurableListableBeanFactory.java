package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.demo.framework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean定义以及预实例化单例的配置接口
 *
 * @author: yinchao
 * @ClassName: ConfigurableListableBeanFactory
 * @Description: 扩展了 ListableBeanFactory 和 AutowireCapableBeanFactory 的接口
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:30
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称获取 BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例Bean
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 添加 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
