package dev.bernouy.cms.feature.website.builder;

import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuilderRepository extends CrudRepository<Builder, String> {

    Builder findFirstByComponentVersionOrderByPositionDesc(String id);

    List<Builder>  findAllByComponentVersionIdOrderByPositionAsc(String id);


}
