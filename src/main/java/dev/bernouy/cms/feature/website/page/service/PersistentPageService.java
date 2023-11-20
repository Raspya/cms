package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.PageRepository;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentPageService {

    @Autowired
    private PageRepository pageRepository;

    public void save(Page page){
        try{ pageRepository.save(page); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void delete(Page page){
        try{ pageRepository.delete(page); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Page getById(String id){
        Page p = pageRepository.findById(id).orElse(null);
        if ( p == null ) throw new MyDatabaseException();
        return p;
    }

    public Page getByPathAndProject(String path, Project project){
        try{
            return pageRepository.findByPathAndProjectId(path, project.getId());
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Page> getProjectsByWebsite(Project website){
        try{
            return pageRepository.findAllByProjectId(website.getId());
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

}
