package dev.bernouy.cms.feature.website.component.version;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.ComponentExceptionMessages;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.tdb.builder.VersionBuilderTDB;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class VersionCreateTest extends BaseTest {

    @Autowired
    private VersionBuilderTDB versionBuilderTDB;

    @Test
    public void testFirstCreate(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        Assertions.assertNotNull(versionTDB.getId());
    }

    @Test
    public void testCreateValidVersionType(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        ReqCreateVersion dto = new ReqCreateVersion("MINOR", versionTDB.getComponentTDB().getId());
        ResponseEntity<String> res = reqTDB.withDto(dto)
                                           .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                                           .send("/api/v1/component/version/create");
        Assertions.assertEquals(201, res.getStatusCode().value());
    }

    @Test
    public void testCreateInvalidVersionType(){
        VersionTDB versionTDB = versionBuilderTDB.build();
        ReqCreateVersion dto = new ReqCreateVersion("Majorr", versionTDB.getComponentTDB().getId());
        ResponseEntity<String> res = reqTDB.withDto(dto)
                                           .withAuth(versionTDB.getComponentTDB().getWebsite().getAccount().getCookie())
                                           .send("/api/v1/component/version/create");
        Assertions.assertEquals(400, res.getStatusCode().value());
    }
}
