package com.demo.framework.beans.factory.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.core.io.Resource;
import com.demo.framework.core.io.ResourceLoader;

/**
 * @author: yinchao
 * @ClassName: BeanDefinitionReader
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:27
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
