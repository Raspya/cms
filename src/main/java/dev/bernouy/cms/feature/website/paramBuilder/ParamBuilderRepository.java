package dev.bernouy.cms.feature.website.paramBuilder;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParamBuilderRepository extends CrudRepository<ParamBuilder, String> {

    List<ParamBuilder> findByParamModelParentIsNullAndBuilderId(String builderId);
    List<ParamBuilder> findAllByParamModelId(String paramModelId);
    List<ParamBuilder> findAllById(String paramBuilderId);
}
