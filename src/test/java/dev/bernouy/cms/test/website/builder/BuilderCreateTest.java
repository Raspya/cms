package dev.bernouy.cms.test.website.builder;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderCreateTest extends BaseTest {

    @Test
    void testCreateBuilder(){
        Builder builder = Assertions.assertDoesNotThrow(() -> builderTDB.build());
        Assertions.assertNotNull(builder.getId());
    }
}
