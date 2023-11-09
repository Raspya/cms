package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.FileService;
import dev.bernouy.cms.config.properties.FileSystemProperties;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystem;

@Service
public class DeploymentVersionService {

    private FileSystemProperties fileSystemProperties;
    private FileService fileService;

    @Autowired
    public DeploymentVersionService(FileSystemProperties fileSystemProperties, FileService fileService) {
        this.fileSystemProperties = fileSystemProperties;
        this.fileService = fileService;
    }

    public void uploadCSS(String content, String versionId){
        String url =
                fileSystemProperties.getFileSystem("root") +
                File.separator + "component" +
                File.separator + "css" +
                File.separator + "C" +
                versionId + ".css";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(content);
            fw.close();
        } catch (Exception e) { throw new BasicException("Error while uploading CSS file"); }
    }

    public void uploadJS(String content, String versionId){
        String url =
                fileSystemProperties.getFileSystem("root") +
                        File.separator + "component" +
                        File.separator + "js" +
                        File.separator + "C" +
                        versionId + ".js";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(content);
            fw.close();
        } catch (Exception e) { throw new BasicException("Error while uploading JS file"); }
    }

    private void parserUploadJS(){

    }

    private void parserUploadCSS(){

    }

}
