package dev.bernouy.cms.test.website.builder;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.version.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderCreateTest extends BaseTest {

    @Test
    void testCreateBuilder(){
        Version version = versionTDB.build();
        Layout layout = layoutTDB.withProject(version.getComponent().getProject()).build();
        Builder builder = Assertions.assertDoesNotThrow(() ->
                builderTDB
                        .withLayout(layout)
                        .withVersion(version).build());
        Assertions.assertNotNull(builder.getId());
    }
}
