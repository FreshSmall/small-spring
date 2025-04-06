package com.demo.framework.beans.factory;

/**
 * @author: yinchao
 * @ClassName: DisposableBean
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/6 23:30
 */
public interface DisposableBean {

    /**
     * 销毁方法
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
