package dev.bernouy.cms.feature.website.component.version;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.tdb.builder.VersionBuilderTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VersionCreateTest extends BaseTest {

    @Autowired
    private VersionBuilderTDB versionBuilderTDB;

    @Test
    public void testCreate(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());
    }
}
