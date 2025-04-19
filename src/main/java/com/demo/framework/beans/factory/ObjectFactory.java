package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: ObjectFactory
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:20
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
