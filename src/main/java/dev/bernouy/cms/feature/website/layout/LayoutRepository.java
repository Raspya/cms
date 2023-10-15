package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LayoutRepository extends CrudRepository<Layout, String> {

    List<Layout> findAllByProject(Project project);
    List<Layout> findAllByABoolean(boolean cond);
}
