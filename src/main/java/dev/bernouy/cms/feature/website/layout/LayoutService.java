package dev.bernouy.cms.feature.website.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutService {

    private LayoutRepository repository;

    @Autowired
    public LayoutService(LayoutRepository repository){
        this.repository = repository;
    }

}
