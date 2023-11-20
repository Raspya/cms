package dev.bernouy.cms.feature.website.layout.service;

import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.LayoutRepository;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentLayoutService {

    @Autowired
    private LayoutRepository layoutRepository;

    public void save(Layout layout){
        try{ layoutRepository.save(layout); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void delete(Layout layout){
        try{ layoutRepository.delete(layout); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Layout getById(String id){
        Layout l = layoutRepository.findById(id).orElse(null);
        if ( l == null ) throw new MyDatabaseException();
        return l;
    }

    public Layout getDefaultLayout(Project project){
        try{
            return layoutRepository.findByDefaultIsAndProjectId(true, project.getId());
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Layout> getAllByProject(Project project){
        try{
            return layoutRepository.findAllByProject(project);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }


}
