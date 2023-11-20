package dev.bernouy.cms.test.website.version;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.dto.req.ReqUploadFileDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionDeployTest extends BaseTest {

    @Test
    void testCreateVersion(){
        Version version = versionTDB.build();
        Assertions.assertDoesNotThrow(() -> versionTDB.build());
        Assertions.assertDoesNotThrow(() -> versionService.uploadCSS(
                new ReqUploadFileDTO("body{background-color: red;}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        Assertions.assertDoesNotThrow(() -> versionService.uploadJS(
                new ReqUploadFileDTO("export default function FTest(props){ return(<h1>Hello World</h1>)}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        Assertions.assertDoesNotThrow(() -> versionService.deploy(version.getComponent().getProject().getOwner(), version.getId()));
    }

    @Test
    void testUploadJs(){
        Version version = versionTDB.build();
        Assertions.assertDoesNotThrow(() -> versionService.uploadJS(
                new ReqUploadFileDTO("export default function FTest(props){ return(<h1>Hello World</h1>)}"),
                version.getComponent().getProject().getOwner(),
                version.getId()
        ));
        //FileReader fileReader = new FileReader( FileSystem. version.getId());
    }


}
