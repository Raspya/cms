package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.dto.res.ResBuilderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingBuilderService {

    public List<ResBuilderDTO> formatBuilders(List<Builder> builders){
        return builders.stream().map(builder ->
                        new ResBuilderDTO(
                                builder.getId(),
                                builder.getComponentVersion().getComponent().getName()))
                .collect(Collectors.toList());
    }
}
