package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回bean实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;


    /**
     * 返回注册表中所有bean名称
     *
     * @return
     */
    public String[] getBeanDefinitionNames();
}
