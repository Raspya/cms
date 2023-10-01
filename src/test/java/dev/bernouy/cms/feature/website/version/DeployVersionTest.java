package dev.bernouy.cms.feature.website.version;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeployVersionTest extends BaseTest {


    @Test
    public void testDeployVersion() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
        versionTDB.setUploadJS("Hello World JS");
        versionTDB.setUploadCSS("Hello World CSS");
        versionTDB.setDeploy();
        Assertions.assertEquals("Hello World JS", versionTDB.getPathJS());
        Assertions.assertEquals("Hello World CSS", versionTDB.getPathCSS());
        Assertions.assertEquals(true, versionTDB.isDeploy());
    }

    @Test
    public void testDeployVersionWithoutJS() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
        versionTDB.setUploadCSS("Hello World CSS");
        versionTDB.setDeploy();
        Assertions.assertEquals("Hello World CSS", versionTDB.getPathCSS());
        Assertions.assertEquals(false, versionTDB.isDeploy());
    }

    @Test
    public void testDeployVersionWithoutCSS() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
        versionTDB.setUploadJS("Hello World JS");
        versionTDB.setDeploy();
        Assertions.assertEquals("Hello World JS", versionTDB.getPathJS());
        Assertions.assertEquals(false, versionTDB.isDeploy());
    }

    @Test
    public void testDeployVersionWithoutJsAndCSS() {
        VersionTDB versionTDB = new VersionTDB().build();
        Assertions.assertNotNull(versionTDB.getId());
        versionTDB.setDeploy();
        Assertions.assertEquals(false, versionTDB.isDeploy());
    }

}
