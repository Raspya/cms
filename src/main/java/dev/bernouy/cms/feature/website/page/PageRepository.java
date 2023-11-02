package dev.bernouy.cms.feature.website.page;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, String> {

    Page findByUrl(String url);

    List<Page> findAllByProjectId(String projectId);
}
