package dev.bernouy.cms.feature.website.page;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, String> {

    Page findByPath(String url);
    Page findByPathAndProjectId(String url, String projectId);

    List<Page> findAllByProjectId(String projectId);
}
