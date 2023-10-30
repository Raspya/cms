package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.feature.website.paramModel.formatting.response.ParamModelFormatting;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.formatting.response.ProjectFormatting;
import dev.bernouy.cms.feature.website.version.formatting.response.FormattingVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingParamModelService {

    public List<ParamModelFormatting> formatProject(List<ParamModel> paramModels){
        return paramModels.stream().map(paramModel ->
                        new ParamModelFormatting(
                                paramModel.getId(),
                                paramModel.getName(),
                                paramModel.getKey(),
                                paramModel.getType(),
                                paramModel.getPosition(),
                                paramModel.getValue()))
                .collect(Collectors.toList());
    }
}
