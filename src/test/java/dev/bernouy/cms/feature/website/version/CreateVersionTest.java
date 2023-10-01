package dev.bernouy.cms.feature.website.version;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateVersionTest extends BaseTest {

    @Test
    public void testCreateVersion() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
    }

    @Test
    public void testCreateInvalidVersionType() {
        VersionTDB versionTDB = new VersionTDB().withTypeVersion("azd").build();
        Assertions.assertNull(versionTDB.getId());
    }

    @Test
    public void testCreateManyVersion() {
        VersionTDB versionTDB1 = new VersionTDB().build();

        VersionTDB versionTDB2 = new VersionTDB().withTypeVersion("patch").withComponent(versionTDB1.getComponent()).build();
        Assertions.assertEquals(1, versionTDB2.getMajorVersion());
        Assertions.assertEquals(0, versionTDB2.getMinorVersion());
        Assertions.assertEquals(1, versionTDB2.getPatchVersion());

        VersionTDB versionTDB3 = new VersionTDB().withTypeVersion("patch").withComponent(versionTDB1.getComponent()).build();
        Assertions.assertEquals(1, versionTDB3.getMajorVersion());
        Assertions.assertEquals(0, versionTDB3.getMinorVersion());
        Assertions.assertEquals(2, versionTDB3.getPatchVersion());

        VersionTDB versionTDB4 = new VersionTDB().withTypeVersion("minor").withComponent(versionTDB1.getComponent()).build();
        Assertions.assertEquals(1, versionTDB4.getMajorVersion());
        Assertions.assertEquals(1, versionTDB4.getMinorVersion());
        Assertions.assertEquals(0, versionTDB4.getPatchVersion());

        VersionTDB versionTDB5 = new VersionTDB().withTypeVersion("minor").withComponent(versionTDB1.getComponent()).build();
        Assertions.assertEquals(1, versionTDB5.getMajorVersion());
        Assertions.assertEquals(2, versionTDB5.getMinorVersion());
        Assertions.assertEquals(0, versionTDB5.getPatchVersion());

        VersionTDB versionTDB6 = new VersionTDB().withTypeVersion("major").withComponent(versionTDB1.getComponent()).build();
        Assertions.assertEquals(2, versionTDB6.getMajorVersion());
        Assertions.assertEquals(0, versionTDB6.getMinorVersion());
        Assertions.assertEquals(0, versionTDB6.getPatchVersion());
    }
}
