package dev.bernouy.cms.lib.fileStorage;

import dev.bernouy.cms.lib.cdn.MyDistribution;
import dev.bernouy.cms.lib.cdn.MyDistributionService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.cloudfront.model.Origin;
import software.amazon.awssdk.services.cloudfront.model.S3OriginConfig;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileStorage {

    private String bucketName;
    private S3Client s3Client;
    private MyDistributionService myDistributionService;

    public MyFileStorage(String bucketName, S3Client s3Client, MyDistributionService myDistributionService){
        this.bucketName = bucketName;
        this.s3Client = s3Client;
        this.myDistributionService = myDistributionService;
    }

    public void upload(String path, String content){
        PutObjectResponse r = s3Client.putObject(b -> b.bucket(bucketName)
                .key(path),
                RequestBody.fromString(content));
        if ( !r.sdkHttpResponse().isSuccessful() ){
            throw new RuntimeException("File upload failed");
        }
    }

    public void uploadDirectory(String path, String pathFileOrFolder){
        File dir = new File(pathFileOrFolder);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String key = path + "/" + file.getName();
                        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();
                        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file.toPath()));
                    } else if (file.isDirectory()) {
                        String newS3Path = path + "/" + file.getName();
                        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(newS3Path + "/")
                                .build();
                        s3Client.putObject(putObjectRequest, RequestBody.empty());
                        uploadDirectory(newS3Path, pathFileOrFolder + "/" + file.getName());
                    }
                }
            }
        }
    }

    public void deleteObject(String path){
        DeleteObjectResponse r = s3Client.deleteObject(b -> b.bucket(bucketName).key(path));
        if ( !r.sdkHttpResponse().isSuccessful() ){
            throw new RuntimeException("File deletion failed");
        }
    }

    /*public void delete(){
        s3Client.deleteBucket(b -> b.bucket(this.bucketName));
    }*/

    private Origin getOriginForCloudFront(String path){
        return Origin.builder()
                .domainName(bucketName + ".s3.amazonaws.com")
                .originPath(path)
                .s3OriginConfig(S3OriginConfig.builder()
                        .originAccessIdentity("origin-access-identity/cloudfront/EQPQ42IXQX78I")
                        .build())
                .id(bucketName)
                .build();
    }

    public MyDistribution createDistribution(String originPath){
        return myDistributionService.create(getOriginForCloudFront(originPath));
    }

    public String getBucketName() {
        return bucketName;
    }
}
