package dev.bernouy.cms.feature.website.component.repository;


import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.servlet.tags.Param;

public interface ParamModelRepository extends CrudRepository<ParamModel, String> {
    ParamModel findByPosition(int i);
    ParamModel findFirstByComponentVersionIdOrderByPositionDesc(String id);
}
