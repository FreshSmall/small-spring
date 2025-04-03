package com.demo.framework.core.io;

/**
 * @author: yinchao
 * @ClassName: Resource
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:14
 */
public interface ResourceLoader {


    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
