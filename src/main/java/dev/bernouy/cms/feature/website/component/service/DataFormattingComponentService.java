package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.formatting.response.InternalComponentFormat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingComponentService {

    public List<InternalComponentFormat> formatSimple(List<Component> components) {
        return components.stream().map(component ->
                        new InternalComponentFormat(
                                component.getId(),
                                component.getName()))
                .collect(Collectors.toList());
    }

}
