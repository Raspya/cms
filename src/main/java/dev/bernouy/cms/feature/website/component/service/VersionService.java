package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.website.component.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    private VersionRepository versionRepository;
    private RegexComponent regexComponent;

    @Autowired
    public VersionService(VersionRepository versionRepository, RegexComponent regexComponent){
        this.regexComponent = regexComponent;
        this.versionRepository = versionRepository;
    }



}
