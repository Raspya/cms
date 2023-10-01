package dev.bernouy.cms.feature.website.paramModel;


import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.repository.CrudRepository;

public interface ParamModelRepository extends CrudRepository<ParamModel, String> {
    ParamModel findByPosition(int i);
    ParamModel findFirstByComponentVersionIdOrderByPositionDesc(String id);
}
