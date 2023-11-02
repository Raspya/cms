package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.formatting.response.BuilderFormatting;
import dev.bernouy.cms.feature.website.paramModel.formatting.response.ParamModelFormatting;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingBuilderService {

    public List<BuilderFormatting> formatBuilders(List<Builder> builders){
        return builders.stream().map(builder ->
                        new BuilderFormatting(
                                builder.getId(),
                                builder.getComponentVersion().getComponent().getName()))
                .collect(Collectors.toList());
    }
}
