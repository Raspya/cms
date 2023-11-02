package dev.bernouy.cms.lib.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudfront.CloudFrontClient;
import software.amazon.awssdk.services.cloudfront.model.*;

@Service
public class MyCloudFront {

    private CloudFrontClient cloudFrontClient;

    public MyCloudFront(){
        cloudFrontClient = CloudFrontClient.builder()
                .region(Region.AWS_GLOBAL)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }


    public String createDistribution(){

        S3OriginConfig s3OriginConfig = S3OriginConfig.builder()
                .originAccessIdentity("")
                .build();

        Origin origin = Origin.builder()
                .s3OriginConfig(s3OriginConfig)
                .build();

        Origins origins = Origins.builder()
                .items(origin)
                .quantity(1)
                .build();

        DistributionConfig distributionConfig = DistributionConfig.builder()
                .callerReference("websiteId")
                .defaultRootObject("index.html")
                .enabled(true)
                .origins(origins)
                .build();

        DistributionConfigWithTags config = DistributionConfigWithTags.builder()
                .tags(Tags.builder().items(Tag.builder().key("website").value("").build()).build())
                .distributionConfig(distributionConfig)
                .build();

        CreateDistributionWithTagsRequest request = CreateDistributionWithTagsRequest.builder()
                .distributionConfigWithTags(config)
                .build();

        CreateDistributionWithTagsResponse response = cloudFrontClient.createDistributionWithTags(request);
    }

}
