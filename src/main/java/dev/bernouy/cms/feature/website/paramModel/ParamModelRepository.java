package dev.bernouy.cms.feature.website.paramModel;


import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ParamModelRepository extends CrudRepository<ParamModel, String> {
    ParamModel findByParentId(String paramModelId);
    ParamModel findFirstByComponentVersionIdOrderByPositionDesc(String id);
    List<ParamModel> findAllByComponentVersionIdOrderByPositionAsc(String id);

    ArrayList<ParamModel> findAllByComponentVersion(String versionId);
    List<ParamModel> findAllByComponentVersionIdAndParentId(String versionId, String parentId);

    List<ParamModel> findAllByParentId(String id);
}
