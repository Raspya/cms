package dev.bernouy.cms.feature.website.component.version;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import dev.bernouy.cms.tdb.builder.VersionBuilderTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VersionDeploy extends BaseTest {

    @Autowired
    private VersionBuilderTDB versionBuilderTDB;

    @Test
    public void testDeploy(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());

        ReqUploadFile reqUploadFile = new ReqUploadFile("Hello World !");
        reqTDB.withDto(reqUploadFile)
              .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
              .send("/api/v1/component/version/" + versionTDB.getId() + "/uploadJS");

        reqTDB.withDto(reqUploadFile)
              .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
              .send("/api/v1/component/version/" + versionTDB.getId() + "/uploadCSS");

        ResponseEntity<String> resDeploy = reqTDB.withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                                                 .send("/api/v1/component/version/" + versionTDB.getId() + "/deploy");

        Assertions.assertEquals(201, resDeploy.getStatusCode().value());
    }

    @Test
    public void testDeployInvalidCSS(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());

        ReqUploadFile reqUploadFile = new ReqUploadFile("Hello World !");
        reqTDB.withDto(reqUploadFile)
                .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/version/" + versionTDB.getId() + "/uploadJS");

        ResponseEntity<String> resDeploy = reqTDB.withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/version/" + versionTDB.getId() + "/deploy");

        Assertions.assertEquals(400, resDeploy.getStatusCode().value());
    }

    @Test
    public void testDeployInvalidJS(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());

        ReqUploadFile reqUploadFile = new ReqUploadFile("Hello World !");
        reqTDB.withDto(reqUploadFile)
                .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/version/" + versionTDB.getId() + "/uploadCSS");

        ResponseEntity<String> resDeploy = reqTDB.withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/version/" + versionTDB.getId() + "/deploy");

        Assertions.assertEquals(400, resDeploy.getStatusCode().value());
    }
    @Test
    public void testDeployInvalidFiles(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());

        ResponseEntity<String> resDeploy = reqTDB.withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/version/" + versionTDB.getId() + "/deploy");

        Assertions.assertEquals(400, resDeploy.getStatusCode().value());
    }
}
