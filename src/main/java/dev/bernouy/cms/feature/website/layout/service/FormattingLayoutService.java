package dev.bernouy.cms.feature.website.layout.service;

import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.dto.res.ResLayoutDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingLayoutService {

    public List<ResLayoutDTO> formatLayouts(List<Layout> layouts){
        return layouts.stream().map(layout ->
                        new ResLayoutDTO(
                                layout.getId(),
                                layout.getName(),
                                layout.isDefault()))
                .collect(Collectors.toList());
    }

    public ResLayoutDTO formatLayout(Layout layout){
        return new ResLayoutDTO(
                layout.getId(),
                layout.getName(),
                layout.isDefault());
    }
}
