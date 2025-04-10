package com.demo.framework.context.support;

import java.util.Collection;
import java.util.Map;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.ConfigurableListableBeanFactory;
import com.demo.framework.beans.factory.config.BeanFactoryPostProcessor;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.demo.framework.context.ApplicationEvent;
import com.demo.framework.context.ApplicationListener;
import com.demo.framework.context.ConfigurableApplicationContext;
import com.demo.framework.context.event.ApplicationEventMulticaster;
import com.demo.framework.context.event.ContextClosedEvent;
import com.demo.framework.context.event.ContextRefreshedEvent;
import com.demo.framework.context.event.SimpleApplicationEventMulticaster;
import com.demo.framework.core.io.DefaultResourceLoader;

/**
 * 抽象应用上下文
 *
 * @author: yinchao
 * @ClassName: AbstractApplicationContext
 * @Description: 应用上下文的抽象实现，提供模板方法模式的骨架实现
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:05
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean
        // 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();

        // 6.初始化事件发布器
        initApplicationEventMulticaster();

        // 7.注册事件监听器
        registerListeners();

        // 8.初始化非延迟加载的单例 Bean
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent (ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void close() {   
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }



    private void registerListeners() {
        Map<String, ApplicationListener> maps = getBeansOfType(ApplicationListener.class);
        Collection<ApplicationListener> values = maps.values();
        for (ApplicationListener listener : values) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 创建 BeanFactory，并加载 BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取 BeanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory
                .getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor processor : beanFactoryPostProcessorMap.values()) {
            processor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册 BeanPostProcessor
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor processor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 返回注册表中所有的 Bean 名称
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 根据名称获取 Bean 对象
     *
     * @param name
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    /**
     * 根据名称和参数列表获取 Bean 对象
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 根据名称和类型获取 Bean 对象
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
