package com.demo.framework.core.io;

import sun.jvm.hotspot.debugger.cdbg.LoadObjectComparator;

import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            return new UrlResource(new URL(location));
        } catch (Exception e) {
            return new FileSystemResource(location);
        }

    }
}
