package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.response.PageFormatting;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.response.ParamBuilderFormatting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingParamBuilderService {

    public List<ParamBuilderFormatting> formatParamBuilders(List<ParamBuilder> paramBuilders){
        return paramBuilders.stream().map(paramBuilder ->
                        new ParamBuilderFormatting(
                                paramBuilder.getId(),
                                paramBuilder.getValue(),
                                paramBuilder.getParamModel().getName(),
                                paramBuilder.getParamModel().getType(),
                                paramBuilder.getParamModel().optionsToMap()))
                .collect(Collectors.toList());
    }
}
