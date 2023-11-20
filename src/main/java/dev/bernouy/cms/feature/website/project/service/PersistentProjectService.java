package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public boolean isDomainAlreadyExist(String domain){
        try{
            return projectRepository.existsByDomain(domain);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Project findById(String id){
        try{
            return projectRepository.findById(id).orElseThrow();
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void save(Project project){
        try{
            projectRepository.save(project);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Project> getProjectsByOwner(Account account){
        try{
            return projectRepository.getProjectsByOwner(account);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

}
