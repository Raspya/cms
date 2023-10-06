package dev.bernouy.cms.feature.website.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private PageRepository repository;

    @Autowired
    public PageService(PageRepository repository) {
        this.repository = repository;
    }

}
