package dev.bernouy.cms.lib.cdn;

import dev.bernouy.cms.common.BasicException;
import software.amazon.awssdk.services.cloudfront.CloudFrontClient;
import software.amazon.awssdk.services.cloudfront.model.CreateInvalidationRequest;
import software.amazon.awssdk.services.cloudfront.model.CreateInvalidationResponse;
import software.amazon.awssdk.services.cloudfront.model.Distribution;
import software.amazon.awssdk.services.cloudfront.model.GetInvalidationResponse;

public class MyDistribution {

    private Distribution distribution;
    private CloudFrontClient cloudFrontClient;

    public MyDistribution(Distribution distribution, CloudFrontClient cloudFrontClient){
        this.distribution = distribution;
    }

    public void reloadCache(){
        CreateInvalidationRequest r = CreateInvalidationRequest.builder()
                .distributionId(distribution.id())
                .invalidationBatch(b -> b.paths(p -> p.items("/*")))
                .build();
        CreateInvalidationResponse response = cloudFrontClient.createInvalidation(r);
        if ( !response.sdkHttpResponse().isSuccessful() ){
            throw new BasicException("Invalidation failed");
        }
    }

    public String getArn(){
        return distribution.arn();
    }

    public String getId(){
        return distribution.id();
    }

}
