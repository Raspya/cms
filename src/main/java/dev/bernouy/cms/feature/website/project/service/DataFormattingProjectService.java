package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.formatting.response.ProjectFormatting;
import dev.bernouy.cms.feature.website.version.formatting.response.FormattingVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingProjectService {

    public List<ProjectFormatting> formatProject(List<Project> projects){
        return projects.stream().map(project ->
                new ProjectFormatting(
                        project.getId(),
                        project.getName(),
                        project.getDomain()))
                .collect(Collectors.toList());
    }
}
