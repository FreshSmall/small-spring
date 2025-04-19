package com.test.framework.bean;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.FactoryBean;
import com.demo.framework.beans.factory.ObjectFactory;
import net.sf.cglib.proxy.Proxy;

/**
 * @author: yinchao
 * @ClassName: HusbandMother
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:25
 */
public class HusbandMother implements FactoryBean<IMother> {

    
    @Override
    public IMother getObject() throws BeansException {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}, (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
