package com.demo.framework.beans.factory.support;

import com.demo.framework.core.io.DefaultResourceLoader;
import com.demo.framework.core.io.ResourceLoader;

/**
 * @author: yinchao
 * @ClassName: AbstractBeanDefinitionReader
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:28
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    
    private final BeanDefinitionRegistry registry;
    
    private ResourceLoader resourceLoader;
    
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }
    
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }
    
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
