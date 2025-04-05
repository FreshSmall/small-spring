package com.demo.framework.context.support;

import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import com.demo.framework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML配置的应用上下文抽象类
 *
 * @author: yinchao
 * @ClassName: AbstractXmlApplicationContext
 * @Description: 提供从XML文件加载BeanDefinition的能力
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:52
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载BeanDefinition
     *
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置文件路径
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
