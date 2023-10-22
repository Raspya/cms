package dev.bernouy.cms.feature.website.paramModel;


import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface ParamModelRepository extends CrudRepository<ParamModel, String> {
    ParamModel findByPosition(int i);
    ParamModel findByParentId(String paramModelId);
    ParamModel findFirstByComponentVersionIdOrderByPositionDesc(String id);
    List<ParamModel> findAllByComponentVersionIdOrderByPositionAsc(String id);

    ArrayList<ParamModel> findAllByComponentVersion(String versionId);
    List<ParamModel> findAllByComponentVersionAndParentId(String versionId, String parentId);
}
