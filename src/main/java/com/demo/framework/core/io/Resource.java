package com.demo.framework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yinchao
 * @ClassName: Resource
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:14
 */
public interface Resource {

    // 定时资源核心加载接口
    InputStream getInputStream() throws IOException;
}
