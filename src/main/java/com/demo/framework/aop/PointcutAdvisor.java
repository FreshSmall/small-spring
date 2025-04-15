package com.demo.framework.aop;


/**
 * @author: yinchao
 * @ClassName: PointcutAdvisor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 22:48
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * Get the Pointcut that drives this advisor.
     */
    PointCut getPointcut();

}
