package dev.bernouy.cms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="app")
public class FileSystemProperties {

    private Map<String, String> fileSystem = new HashMap<>();

    public String getFileSystem(String fileSystem){
        return this.fileSystem.get(fileSystem);
    }

    public Map<String, String> getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(Map<String, String> fileSystem) {
        this.fileSystem = fileSystem;
    }
}
