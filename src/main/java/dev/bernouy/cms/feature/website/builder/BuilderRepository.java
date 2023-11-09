package dev.bernouy.cms.feature.website.builder;

import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuilderRepository extends CrudRepository<Builder, String> {

    Builder findFirstByComponentVersionOrderByPositionDesc(String id);

    Builder findFirstBuilderByPageId(String id);

    Builder findFirstBuilderByLayoutId(String id);

    List<Builder>  findAllByComponentVersionIdOrderByPositionAsc(String id);

    List<Builder> findAllByPageId(String pageId);

    List<Builder> findAllByLayoutId(String layoutId);


}
