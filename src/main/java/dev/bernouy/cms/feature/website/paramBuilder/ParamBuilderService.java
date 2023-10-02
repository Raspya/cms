package dev.bernouy.cms.feature.website.paramBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamBuilderService {

    private ParamBuilderRepository repository;

    @Autowired
    public ParamBuilderService(ParamBuilderRepository repository) {
        this.repository = repository;
    }
}
