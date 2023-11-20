package dev.bernouy.cms.test.website.website;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.project.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebsiteCreateTest extends BaseTest {

    @Test
    void testCreateWebsite(){
        Project website = Assertions.assertDoesNotThrow(() -> websiteTDB.build());
        Assertions.assertNotNull(website.getId());
    }

}
