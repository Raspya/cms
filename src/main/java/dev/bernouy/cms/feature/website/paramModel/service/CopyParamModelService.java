package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelFloat;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelInt;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelString;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyParamModelService {

    private PersistentParamModelService persistentParamModelService;

    @Autowired
    public CopyParamModelService(PersistentParamModelService persistentParamModelService) {
        this.persistentParamModelService = persistentParamModelService;
    }

    public ParamModel copyFromParamModel(ParamModel oldParamModel, Version newVersion, String parentId){
        ParamModel paramModel = switch (oldParamModel.getType()) {
            case "string" -> new ParamModelString(((ParamModelString) oldParamModel).getMin(), ((ParamModelString) oldParamModel).getMax());
            case "int"    -> new ParamModelInt(((ParamModelInt) oldParamModel).getMin(), ((ParamModelInt) oldParamModel).getMax());
            case "float"  -> new ParamModelFloat(((ParamModelFloat) oldParamModel).getMin(), ((ParamModelFloat) oldParamModel).getMax());
            default       -> throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_TYPE);
        };
        paramModel.setComponentVersion(newVersion);
        paramModel.setParent(persistentParamModelService.getById(parentId));
        paramModel.setName(oldParamModel.getName());
        paramModel.setKey(oldParamModel.getKey());
        paramModel.setType(oldParamModel.getType());
        paramModel.setValue(oldParamModel.getValue());
        paramModel.setPosition(oldParamModel.getPosition());
        return paramModel;
    }

}
