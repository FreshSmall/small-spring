package com.demo.framework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yinchao
 * @ClassName: ClassPathResource
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:15
 */
public class ClassPathResource implements Resource {

    private final String path;
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : Thread.currentThread().getContextClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("Could not find resource '" + path + "' in classpath");
        }
        return is;
    }
    
    public String getPath() {
        return path;
    }
}
