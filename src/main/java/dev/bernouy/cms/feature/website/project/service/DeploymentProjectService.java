package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.FileService;
import dev.bernouy.cms.config.properties.BucketsProperties;
import dev.bernouy.cms.config.properties.FileSystemProperties;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.lib.cdn.MyDistribution;
import dev.bernouy.cms.lib.cdn.MyDistributionService;
import dev.bernouy.cms.lib.fileStorage.MyFileStorage;
import dev.bernouy.cms.lib.fileStorage.MyFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
public class DeploymentProjectService {

    private BucketsProperties bucketsProperties;
    private FileSystemProperties fileSystem;
    private MyDistributionService myDistributionService;
    private MyFileStorageService myFileStorageService;
    private DataPersistentProjectService datePersistenceProjectService;

    @Autowired
    public DeploymentProjectService(FileSystemProperties fileSystem, MyDistributionService myDistributionService, MyFileStorageService myFileStorageService, BucketsProperties bucketsProperties, DataPersistentProjectService datePersistenceProjectService ){
        this.fileSystem = fileSystem;
        this.myDistributionService = myDistributionService;
        this.myFileStorageService = myFileStorageService;
        this.bucketsProperties = bucketsProperties;
        this.datePersistenceProjectService = datePersistenceProjectService;
    }

    public void initWebsiteFolder(Project project){
        try{
            FileService.copyDirectory(
                    Path.of(fileSystem.getFileSystem("root") + "/structure/model"),
                    Path.of(fileSystem.getFileSystem("root") + "/structure/websites/" + project.getId())
            );
        } catch ( Exception e ){ e.printStackTrace(); }
        MyFileStorage f = myFileStorageService.getBucket(bucketsProperties.getBucket("websites"));
        buildAndDeploy(project.getId());
        MyDistribution d = f.createDistribution("/websites/" + project.getId());
        project.setDistributionId(d.getId());
        datePersistenceProjectService.save(project);
    }

    public void buildAndDeploy(String websiteId){
        File workingDirectory = new File(fileSystem.getFileSystem("root") + "/structure/websites/" + websiteId);
        System.out.println(workingDirectory.getAbsolutePath());
        String command = "npm run build";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.directory(workingDirectory);

        try{
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if ( exitCode != 0 ) throw new BasicException("Error while building website");
        } catch ( Exception e ){ throw new BasicException("Error while building website"); }
        MyFileStorage f = myFileStorageService.getBucket(bucketsProperties.getBucket("websites"));
        f.uploadDirectory("websites/" + websiteId, fileSystem.getFileSystem("root") + "/structure/websites/" + websiteId + "/build");
    }

}
