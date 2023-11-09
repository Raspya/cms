package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParamBuilderRepository extends CrudRepository<ParamBuilder, String> {

    List<ParamBuilder> findAllByBuilderIdAndParamModelParentId(String builderId, String paramModelId);
    List<ParamBuilder> findByParamModelParentIsNullAndBuilderId(String builderId);

    List<ParamBuilder> findAllByBuilderIdAndParamModelId(String builderId, String paramModelId);
    List<ParamBuilder> findAllByParamModelId(String paramModelId);
    List<ParamBuilder> findAllById(String paramBuilderId);
}
