package com.test.framework.bean;

import com.demo.framework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: yinchao
 * @ClassName: UserServiceBeforeAdvice
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/18 23:01
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("方法已经被执行,method:" + method.getName());
    }
}
