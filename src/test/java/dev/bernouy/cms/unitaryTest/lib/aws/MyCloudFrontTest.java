package dev.bernouy.cms.unitaryTest.lib.aws;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.lib.aws.MyCloudFront;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyCloudFrontTest extends BaseTest {

    @Autowired
    private MyCloudFront myCloudFront;

    @Test
    public void createWebsiteDistribution(){
        myCloudFront.createWebsiteDistribution("websiteId");
    }

}
