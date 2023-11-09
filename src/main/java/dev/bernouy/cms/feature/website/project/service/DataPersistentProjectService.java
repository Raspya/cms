package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataPersistentProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public boolean isDomainAlreadyExist(String domain){
        return projectRepository.existsByDomain(domain);
    }

    public void save(Project project){
        projectRepository.save(project);
    }

}
