package dev.bernouy.cms.feature.website.version;

import dev.bernouy.cms.feature.website.component.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VersionRepository extends CrudRepository<Version, String> {

    Version getFirstByComponent_IdOrderByMajorVersionDescMinorVersionDescPatchVersionDesc(String componentId);
    List<Version> getVersionByComponentId(String componentId);

}
