package com.demo.framework.context;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: ConfigurableApplicationContext
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:02
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册关闭钩子
     */
    void registerShutdownHook();

    /**
     * 关闭容器
     */
    void close();
}
