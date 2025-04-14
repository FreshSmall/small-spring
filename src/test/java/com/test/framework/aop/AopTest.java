package com.test.framework.aop;

import com.demo.framework.aop.AdvisedSupport;
import com.demo.framework.aop.TargetSource;
import com.demo.framework.aop.aspectj.AspectJExpressionPointcut;
import com.demo.framework.aop.framework.Cglib2AopProxy;
import com.demo.framework.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

/**
* @author: yinchao
* @ClassName: AopTest
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:40
*/
public class AopTest {

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.test.framework.aop.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }
}
