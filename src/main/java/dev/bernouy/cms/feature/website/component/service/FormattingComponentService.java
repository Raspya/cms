package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.dto.res.ResComponentDTO_VInternalComponent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingComponentService {

    public List<ResComponentDTO_VInternalComponent> formatSimple(List<Component> components) {
        return components.stream().map(component ->
                        new ResComponentDTO_VInternalComponent(
                                component.getId(),
                                component.getName()))
                .collect(Collectors.toList());
    }

}
