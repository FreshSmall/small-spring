package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
