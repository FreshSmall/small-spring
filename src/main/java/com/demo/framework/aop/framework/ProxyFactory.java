package com.demo.framework.aop.framework;

import com.demo.framework.aop.AdvisedSupport;

/**
 * @author: yinchao
 * @ClassName: ProxyFactory
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 22:58
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
