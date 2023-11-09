package dev.bernouy.cms.lib.cdn;

import dev.bernouy.cms.lib.fileStorage.MyFileStorage;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudfront.CloudFrontClient;
import software.amazon.awssdk.services.cloudfront.model.*;

import java.time.Instant;

@Service
public class MyDistributionService {

    private CloudFrontClient cloudFrontClient;

    public MyDistributionService(){
        this.cloudFrontClient = CloudFrontClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.EU_WEST_3)
                .build();
    }

    public MyDistribution create(Origin origin){
        CreateDistributionResponse createDistResponse = cloudFrontClient.createDistribution(builder -> builder
                .distributionConfig(b1 -> b1
                        .origins(
                                Origins.builder()
                                        .quantity(1)
                                        .items(origin)
                                        .build())
                        .defaultCacheBehavior(b2 -> b2
                                .viewerProtocolPolicy(ViewerProtocolPolicy.ALLOW_ALL)
                                .targetOriginId(origin.id())
                                .minTTL(200L)
                                .forwardedValues(b5 -> b5
                                        .cookies(cp -> cp
                                                .forward(ItemSelection.NONE))
                                        .queryString(true))
                                .allowedMethods(b4 -> b4
                                        .quantity(2)
                                        .items(Method.HEAD, Method.GET)
                                        .cachedMethods(b5 -> b5
                                                .quantity(2)
                                                .items(Method.HEAD, Method.GET))))
                        .customErrorResponses(b3 -> b3
                                .quantity(1)
                                .items(b4 -> b4
                                        .errorCode(403)
                                        .responseCode("200")
                                        .responsePagePath("/index.html")))
                        .enabled(true)
                        .comment("Distribution built with java")
                        .callerReference(Instant.now().toString())
                ));
        if ( !createDistResponse.sdkHttpResponse().isSuccessful() ) {
            throw new RuntimeException("Distribution creation failed");
        }
        return new MyDistribution(createDistResponse.distribution(), cloudFrontClient);
    }

    public MyDistribution getDistributionById(String id){
        GetDistributionRequest r = GetDistributionRequest.builder()
                .id(id)
                .build();
        GetDistributionResponse response = cloudFrontClient.getDistribution(r);
        if ( !response.sdkHttpResponse().isSuccessful() ) {
            throw new RuntimeException("Distribution retrieval failed");
        }
        return new MyDistribution(response.distribution(), cloudFrontClient);
    }

    public Origin createOrigin(String domain, String originPath){
        return Origin.builder()
                .domainName(domain)
                .originPath(originPath)
                .id("S3-Website")
                .build();
    }

    public Origin createS3Origin(String name, String path){
        return Origin.builder()
                .domainName(name + ".s3.amazonaws.com")
                .originPath(path)
                .id("MyS3Origin")
                .s3OriginConfig(S3OriginConfig.builder()
                    .originAccessIdentity("origin-access-identity/cloudfront/")
                    .build())
                .build();
    }

}
