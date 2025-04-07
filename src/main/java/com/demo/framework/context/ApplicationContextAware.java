package com.demo.framework.context;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.Aware;

/**
 * @author: yinchao
 * @ClassName: ApplicationContextAware
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/7 23:24
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
