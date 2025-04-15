package com.demo.framework.aop.aspectj;

import com.demo.framework.aop.PointCut;
import com.demo.framework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author: yinchao
 * @ClassName: AspectJExpressionPointcutAdvisor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 22:48
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public PointCut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }
}
