package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.dto.res.ResProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingProjectService {

    public List<ResProjectDTO> formatProject(List<Project> projects){
        return projects.stream().map(project ->
                new ResProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getDomain()))
                .collect(Collectors.toList());
    }

    public ResProjectDTO formatProject(Project project){
        return new ResProjectDTO(
                project.getId(),
                project.getName(),
                project.getDomain());
    }
}
