package dev.bernouy.cms.lib.fileStorage;

import com.mongodb.client.model.BucketOptions;
import dev.bernouy.cms.lib.cdn.MyDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;

import java.nio.file.Paths;

@Service
public class MyFileStorageService {

    private final S3Client s3Client;
    @Autowired
    private MyDistributionService myDistributionService;

    public MyFileStorageService(){
        s3Client = S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.EU_WEST_3)
                .build();
    }

    public MyFileStorage getBucket(String bucketName){
        HeadBucketResponse r =  s3Client.headBucket(b -> b.bucket(bucketName));
        if ( !r.sdkHttpResponse().isSuccessful() ){
            throw new RuntimeException("Bucket not found");
        }
        return new MyFileStorage(bucketName, s3Client, myDistributionService);
    }

    public MyFileStorage createBucket(String bucketName){
        CreateBucketResponse r = s3Client.createBucket(b -> b.bucket(bucketName));
        if ( !r.sdkHttpResponse().isSuccessful() ){
            throw new RuntimeException("Bucket creation failed");
        }
        return new MyFileStorage(bucketName, s3Client, myDistributionService);
    }



}
