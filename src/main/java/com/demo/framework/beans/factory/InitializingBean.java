package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: InitializingBean
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/6 23:29
 */
public interface InitializingBean {

    /**
     * bean属性设置完成后调用
     *
     * @throws BeansException
     */
    void afterPropertiesSet() throws BeansException;
}
