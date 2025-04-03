package com.demo.framework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: yinchao
 * @ClassName: DefaultResourceLoader
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:22
 */
public class DefaultResourceLoader implements ResourceLoader {
    
    @Override
    public Resource getResource(String location) {
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null");
        }
        
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 处理类路径资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 尝试当作 URL 处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 不是有效的 URL，作为文件系统资源处理
                return new FileSystemResource(location);
            }
        }
    }
}
