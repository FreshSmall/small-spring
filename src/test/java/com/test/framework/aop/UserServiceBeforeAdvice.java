package com.test.framework.aop;

import com.demo.framework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: yinchao
 * @ClassName: UserServiceBeforeAdvice
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 23:25
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
