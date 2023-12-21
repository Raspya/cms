package dev.bernouy.cms.test.website.paramBuilder;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqCreateParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.dto.res.ResParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.version.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParamBuilderCreateTest extends BaseTest {

    @Test
    void testCreateParamBuilder(){
        Version version = versionTDB.build();
        ParamModel paramModel = paramModelTDB.withVersion(version).withType("LIST").build();
        ParamModel paramModelString = paramModelTDB.withParent(paramModel).withType("STRING").build();
        Builder builder = builderTDB.withVersion(version).build();
        List<ResParamBuilderDTO> res = paramBuilderService.listAllParamBuilder(builder.getId(), null, version.getComponent().getProject().getOwner());
        System.out.println(res);
        // Vérifier si tous les paramBuilder de base ont bien été créés
    }
}
