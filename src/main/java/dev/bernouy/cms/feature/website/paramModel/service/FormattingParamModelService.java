package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.feature.website.paramModel.dto.response.ResParamModelDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingParamModelService {

    public List<ResParamModelDTO> formatParamModels(List<ParamModel> paramModels){
        return paramModels.stream().map(paramModel ->
                        new ResParamModelDTO(
                                paramModel.getId(),
                                paramModel.getName(),
                                paramModel.getKey(),
                                paramModel.getType(),
                                paramModel.getPosition(),
                                paramModel.getValue(),
                                paramModel.optionsToMap()))
                .collect(Collectors.toList());
    }

    public ResParamModelDTO formatParamModel(ParamModel paramModel){
        return new ResParamModelDTO(
                paramModel.getId(),
                paramModel.getName(),
                paramModel.getKey(),
                paramModel.getType(),
                paramModel.getPosition(),
                paramModel.getValue(),
                paramModel.optionsToMap());
    }
}
