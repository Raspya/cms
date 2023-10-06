package dev.bernouy.cms.feature.website.version;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UploadCSSVersionTest extends BaseTest {

    @Test
    public void testUploadCSSVersion() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
        versionTDB.setUploadCSS("Hello World");
        Assertions.assertEquals("Hello World", versionTDB.getPathCSS());
    }

}
