package dev.bernouy.cms.feature.website.version;

import org.springframework.data.repository.CrudRepository;

public interface VersionRepository extends CrudRepository<Version, String> {

    Version getFirstByComponent_IdOrderByMajorVersionDescMinorVersionDescPatchVersionDesc(String componentId);
}
