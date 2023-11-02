package dev.bernouy.cms.lib.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class MyS3 {

    private S3Client s3Client;
    private String bucketName;

    public MyS3(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public MyS3(S3Client s3Client, String bucketName){
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public void createBucket(String bucketName){
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
    }

}
