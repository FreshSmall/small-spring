package com.demo.framework.aop;

import java.lang.reflect.Method;

/**
* @author: yinchao
* @ClassName: MethodMatcher
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:19
*/
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
