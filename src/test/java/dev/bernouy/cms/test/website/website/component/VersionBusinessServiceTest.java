package dev.bernouy.cms.test.website.website.component;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.formatting.response.InternalComponentFormat;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.formatting.request.ReqUploadFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.List;

public class VersionBusinessServiceTest extends BaseTest {

    @Test
    void testCreateVersion(){
        Version version = versionTDB.build();
        Assertions.assertDoesNotThrow(() -> versionTDB.build());
        Assertions.assertDoesNotThrow(() -> versionService.uploadCSS(
                new ReqUploadFile("body{background-color: red;}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        Assertions.assertDoesNotThrow(() -> versionService.uploadJS(
                new ReqUploadFile("export default function FTest(props){ return(<h1>Hello World</h1>)}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        Assertions.assertDoesNotThrow(() -> versionService.deploy(version.getComponent().getProject().getOwner(), version.getId()));
    }

    @Test
    void testUploadJs(){
        Version version = versionTDB.build();
        Assertions.assertDoesNotThrow(() -> versionService.uploadJS(
                new ReqUploadFile("export default function FTest(props){ return(<h1>Hello World</h1>)}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        FileReader fileReader = new FileReader( FileSystem. version.getId());
    }


}
