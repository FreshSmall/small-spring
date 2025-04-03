package com.demo.framework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yinchao
 * @ClassName: FileSystemResource
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:15
 */
public class FileSystemResource implements Resource {
    
    private final File file;
    private final String path;
    
    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }
    
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
    
    public String getPath() {
        return path;
    }
}
