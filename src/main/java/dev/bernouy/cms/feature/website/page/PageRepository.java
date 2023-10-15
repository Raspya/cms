package dev.bernouy.cms.feature.website.page;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, String> {

    Page findByUrl(String url);
}
