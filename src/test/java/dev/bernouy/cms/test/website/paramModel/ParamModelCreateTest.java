package dev.bernouy.cms.test.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.version.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamModelCreateTest extends BaseTest {

    @Test
    void testCreateParamModel(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.build());
        Assertions.assertNotNull(paramModel.getId());
    }
}
