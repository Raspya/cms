package dev.bernouy.cms.feature.website.component;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentRepository extends CrudRepository<Component, String> {
    List<Component> getComponentsByProjectId( String websiteId );
}