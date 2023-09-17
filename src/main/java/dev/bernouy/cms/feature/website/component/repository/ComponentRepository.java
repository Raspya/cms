package dev.bernouy.cms.feature.website.component.repository;

import dev.bernouy.cms.feature.website.component.model.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentRepository extends CrudRepository<Component, String> {
    List<Component> getComponentsByProjectId( String websiteId );
}
