package com.demo.framework.beans.factory.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.DisposableBean;
import com.demo.framework.beans.factory.ObjectFactory;
import com.demo.framework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 一级缓存，普通对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，提前暴漏对象，没有完全实例化的对象
    protected final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    // 三级缓存，存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        //return singletonObjects.get(beanName);
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = disposableBeans.keySet();
        for (String beanName : keySet) {
            DisposableBean bean = disposableBeans.remove(beanName);
            try {
                bean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        this.singletonObjects.put(beanName, singletonObject);
    }

    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    protected void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {
        disposableBeans.put(beanName, disposableBeanAdapter);
    }
}
