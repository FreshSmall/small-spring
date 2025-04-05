package com.demo.framework.context.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.ConfigurableListableBeanFactory;
import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 可刷新的应用上下文抽象类
 *
 * @author: yinchao
 * @ClassName: AbstractRefreshableApplicationContext
 * @Description: 提供创建和刷新BeanFactory的能力
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:54
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建BeanFactory并加载BeanDefinition
     *
     * @throws BeansException
     */
    @Override
    protected final void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建BeanFactory
     *
     * @return
     */
    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinition
     *
     * @param beanFactory
     * @throws BeansException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    /**
     * 获取BeanFactory
     *
     * @return
     */
    @Override
    public final ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
