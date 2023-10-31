package dev.bernouy.cms.feature.website.layout.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.formatting.response.BuilderFormatting;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.formatting.response.LayoutFormatting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingLayoutService {

    public List<LayoutFormatting> formatLayouts(List<Layout> layouts){
        return layouts.stream().map(layout ->
                        new LayoutFormatting(
                                layout.getId(),
                                layout.getName(),
                                layout.isaBoolean()))
                .collect(Collectors.toList());
    }
}
