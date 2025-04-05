package com.demo.framework.context.support;

import com.demo.framework.beans.BeansException;

/**
 * 基于类路径XML配置的应用上下文
 *
 * @author: yinchao
 * @ClassName: ClassPathXmlApplicationContext
 * @Description: 从类路径加载XML配置文件的应用上下文
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:50
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 从XML文件加载BeanDefinition，并刷新上下文
     *
     * @param configLocation 配置文件路径
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从多个XML文件加载BeanDefinition，并刷新上下文
     *
     * @param configLocations 配置文件路径数组
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
