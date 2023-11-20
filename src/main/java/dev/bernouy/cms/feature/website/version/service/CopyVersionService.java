package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.CopyParamModelService;
import dev.bernouy.cms.feature.website.paramModel.service.PersistentParamModelService;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyVersionService {

    private PersistentParamModelService persistentParamModelService;
    private CopyParamModelService copyParamModelService;

    @Autowired
    public CopyVersionService(PersistentParamModelService persistentParamModelService, CopyParamModelService copyParamModelService) {
        this.persistentParamModelService = persistentParamModelService;
        this.copyParamModelService = copyParamModelService;
    }

    public void duplicateSettings(Version version, String parentId) {
        ParamModel parent = persistentParamModelService.getById(parentId);
        List<ParamModel> lstParamModel = persistentParamModelService.findAllByVersionAndParent(version, parent);
        ParamModel paramModelTmp;
        for (ParamModel paramModel : lstParamModel) {
            paramModelTmp = copyParamModelService.copyFromParamModel(paramModel, version, parentId);
            persistentParamModelService.save(paramModelTmp);
            duplicateSettings(version, paramModelTmp.getId());
        }
    }

}
