package com.demo.framework.aop;

/**
 * @author: yinchao
 * @ClassName: PointCut
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/14 23:18
 */
public interface PointCut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
