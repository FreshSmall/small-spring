package com.demo.framework.beans.factory;

/**
 * @author: yinchao
 * @ClassName: FactoryBean
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/8 23:14
 */
public interface FactoryBean<T>{

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
