package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.res.ResParamBuilderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingParamBuilderService {

    public List<ResParamBuilderDTO> formatParamBuilders(List<ParamBuilder> paramBuilders){
        return paramBuilders.stream().map(paramBuilder ->
                        new ResParamBuilderDTO(
                                paramBuilder.getId(),
                                paramBuilder.getValue(),
                                paramBuilder.getParamModel().getName(),
                                paramBuilder.getParamModel().getType(),
                                paramBuilder.getParamModel().optionsToMap()))
                .collect(Collectors.toList());
    }
}
