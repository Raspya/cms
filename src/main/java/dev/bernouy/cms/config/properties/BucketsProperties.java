package dev.bernouy.cms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="app.bucket")
public class BucketsProperties {

    private String oai;
    private Map<String, String> buckets = new HashMap<>();

    public String getOai() {
        return oai;
    }

    public void setOai(String oai) {
        this.oai = oai;
    }

    public Map<String, String> getBuckets() {
        return buckets;
    }

    public String getBucket(String bucket){
        return buckets.get(bucket);
    }

    public void setBuckets(Map<String, String> buckets) {
        this.buckets = buckets;
    }

}
