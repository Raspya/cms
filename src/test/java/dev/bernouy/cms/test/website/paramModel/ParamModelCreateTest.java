package dev.bernouy.cms.test.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.paramModel.ParamModelRepository;
import dev.bernouy.cms.feature.website.paramModel.dto.req.ReqOptionParamModelDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.PersistentParamModelService;
import dev.bernouy.cms.feature.website.version.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ParamModelCreateTest extends BaseTest {


    @Test
    void testCreateParamModel(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.build());
        Assertions.assertNotNull(paramModel.getId());
    }

    @Test
    void testCreateParamModelString(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.withType("STRING").build());
        Assertions.assertNotNull(paramModel.getId());

        // Test De l'option min
        ReqOptionParamModelDTO reqOptionParamModelDTO = new ReqOptionParamModelDTO();
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("0");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de l'option max
        reqOptionParamModelDTO.setKey("max");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("10");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de la verif entre max et min ( max ne peut pas être inférieur à min )
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("11");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

    }

    @Test
    void testCreateParamModelInt(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.withType("INT").build());
        Assertions.assertNotNull(paramModel.getId());

        // Test De l'option min
        ReqOptionParamModelDTO reqOptionParamModelDTO = new ReqOptionParamModelDTO();
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("0");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de l'option max
        reqOptionParamModelDTO.setKey("max");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("10");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de la verif entre max et min ( max ne peut pas être inférieur à min )
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("11");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

    }

    @Test
    void testCreateParamModelFloat(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.withType("FLOAT").build());
        Assertions.assertNotNull(paramModel.getId());

        // Test De l'option min
        ReqOptionParamModelDTO reqOptionParamModelDTO = new ReqOptionParamModelDTO();
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("0");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de l'option max
        reqOptionParamModelDTO.setKey("max");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("10");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de la verif entre max et min ( max ne peut pas être inférieur à min )
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("11");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

    }

    @Test
    void testCreateParamModelObject(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.withType("OBJECT").build());
        Assertions.assertNotNull(paramModel.getId());

        // Ajout de trois enfants (int, object, string)
        ParamModel paramModelInt = Assertions.assertDoesNotThrow(() -> paramModelTDB.withParent(paramModel).withType("INT").build());
        ParamModel paramModelObject = Assertions.assertDoesNotThrow(() -> paramModelTDB.withParent(paramModel).withType("OBJECT").build());
        ParamModel paramModelString = Assertions.assertDoesNotThrow(() -> paramModelTDB.withParent(paramModel).withType("STRING").build());
        Assertions.assertNotNull(paramModelInt.getId());
        Assertions.assertNotNull(paramModelObject.getId());
        Assertions.assertNotNull(paramModelString.getId());
        Assertions.assertEquals(paramModelInt.getParent(), paramModel.getId());
        Assertions.assertEquals(paramModelObject.getParent(), paramModel.getId());
        Assertions.assertEquals(paramModelString.getParent(), paramModel.getId());
    }

    @Test
    void testCreateParamModelList(){
        ParamModel paramModel = Assertions.assertDoesNotThrow(() -> paramModelTDB.withType("LIST").build());

        // Ajout d'un enfant de type String
        ParamModel paramModelChildren = Assertions.assertDoesNotThrow(() -> paramModelTDB.withParent(paramModel).withType("STRING").build());
        Assertions.assertNotNull(paramModel.getId());
        Assertions.assertNotNull(paramModelChildren.getId());
        Assertions.assertEquals(paramModelChildren.getParent(), paramModel.getId());
        Assertions.assertNotNull(paramModelChildren.getComponentVersion());



        // Test De l'option min
        ReqOptionParamModelDTO reqOptionParamModelDTO = new ReqOptionParamModelDTO();
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("0");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de l'option max
        reqOptionParamModelDTO.setKey("max");
        reqOptionParamModelDTO.setValue("-1");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
        reqOptionParamModelDTO.setValue("10");
        Assertions.assertDoesNotThrow(() -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));

        // Test de la verif entre max et min ( max ne peut pas être inférieur à min )
        reqOptionParamModelDTO.setKey("min");
        reqOptionParamModelDTO.setValue("11");
        Assertions.assertThrows(Exception.class, () -> paramModelService.patchOption(reqOptionParamModelDTO, paramModel.getComponentVersion().getComponent().getProject().getOwner(), paramModel.getId()));
    }
}
