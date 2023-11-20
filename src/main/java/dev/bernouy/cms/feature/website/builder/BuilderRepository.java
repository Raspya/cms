package dev.bernouy.cms.feature.website.builder;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuilderRepository extends CrudRepository<Builder, String> {

    Builder findFirstBuilderByPageIdOrderByPositionDesc(String id);

    Builder findFirstBuilderByLayoutIdOrderByPositionDesc(String id);

    List<Builder> findAllByPageIdOrderByPositionAsc(String pageId);

    List<Builder> findAllByLayoutIdOrderByPositionAsc(String layoutId);

}
