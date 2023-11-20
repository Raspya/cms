package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentComponentService {

    private ComponentRepository componentRepository;

    @Autowired
    public PersistentComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Component getById(String id){
        try{
            return componentRepository.findById(id).orElseThrow();
        } catch ( Exception e ) {
            throw new BasicException("Component not found");
        }
    }

    public List<Component> getComponentsByProjectId(String websiteId){
        try{
            return componentRepository.getComponentsByProjectId(websiteId);
        } catch ( Exception e ) {
            throw new BasicException("Error during detail components");
        }
    }

    public void save(Component component){
        try{
            componentRepository.save(component);
        } catch ( Exception e ) {
            throw new BasicException("Error during save component");
        }
    }

    public void delete(Component component){
        try{
            componentRepository.delete(component);
        } catch ( Exception e ) {
            throw new BasicException("Error during delete component");
        }
    }

}
