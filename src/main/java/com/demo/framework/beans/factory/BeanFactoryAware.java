package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: BeanFactoryAware
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/7 23:21
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
