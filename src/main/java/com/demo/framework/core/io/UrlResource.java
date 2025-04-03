package com.demo.framework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: yinchao
 * @ClassName: UrlResource
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:16
 */
public class UrlResource implements Resource {
    
    private final URL url;
    
    public UrlResource(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("URL must not be null");
        }
        this.url = url;
    }
    
    public UrlResource(String url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("URL must not be null");
        }
        this.url = new URL(url);
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        }
        catch (IOException ex) {
            // 如果是 HTTP 连接，尝试关闭连接
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
    
    public URL getUrl() {
        return this.url;
    }
}
