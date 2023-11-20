package dev.bernouy.cms.test.website.version;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionCreateTest extends BaseTest {

    @Test
    void testCreateVersion(){
        Version version = Assertions.assertDoesNotThrow(() -> versionTDB.build());
        Assertions.assertNotNull(version.getId());
    }
}
