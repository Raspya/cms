package dev.bernouy.cms.feature.website.component.version;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import dev.bernouy.cms.tdb.builder.VersionBuilderTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class VersionUploadCSSTest extends BaseTest {

    @Autowired
    private VersionBuilderTDB versionBuilderTDB;

    @Test
    public void testUpload(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        ReqUploadFile reqUploadFile = new ReqUploadFile("Hello World !");
        ResponseEntity<String> res = reqTDB.withDto(reqUploadFile)
              .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
              .send("/api/v1/component/version/" + versionTDB.getId() + "/uploadCSS");

        Assertions.assertEquals(201, res.getStatusCode().value());
    }
}
