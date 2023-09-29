package dev.bernouy.cms.feature.website.component.repository;

import dev.bernouy.cms.feature.website.component.model.Version;
import org.springframework.data.repository.CrudRepository;

public interface VersionRepository extends CrudRepository<Version, String> {

    Version getFirstByComponent_IdOrderByMajorVersionDescMinorVersionDescPatchVersionDesc(String componentId);
}
