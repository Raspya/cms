package dev.bernouy.cms.feature.website.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuilderService {

    private BuilderRepository repository;

    @Autowired
    public BuilderService(BuilderRepository repository){
        this.repository = repository;
    }


}
