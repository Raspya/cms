package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.ComponentRepository;
import dev.bernouy.cms.feature.website.component.formatting.response.InternalComponentFormat;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogicComponentService {

    private ComponentRepository componentRepository;
    private RegexComponent regexVerification;
    private BusinessLogicProjectService websiteService;
    private DataFormattingComponentService dataFormattingComponentService;

    @Autowired
    public BusinessLogicComponentService(ComponentRepository componentRepository, RegexComponent regexVerification, BusinessLogicProjectService websiteService, DataFormattingComponentService dataFormattingComponentService ){
        this.componentRepository = componentRepository;
        this.regexVerification = regexVerification;
        this.websiteService = websiteService;
        this.dataFormattingComponentService = dataFormattingComponentService;
    }

    public void isUserAuthorized( String componentId, Account account ){
        Component component = null;
        try{
            component = componentRepository.findById( componentId ).orElseThrow();
        } catch ( Exception e ){
            throw new BasicException( BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN );
        }
        if ( !component.getProject().getOwner().equals( account ) )
            throw new BasicException( BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN );
    }

    public Component create( String name, String websiteId, Account account ){
        regexVerification.isNameValid( name );
        websiteService.isOwner( websiteId, account );
        Project website = websiteService.getWebsite( websiteId );
        Component component = new Component();
        component.setName( name );
        component.setProject( website );
        componentRepository.save( component );
        return component;
    }

    public void editName( String name, String componentId, Account account ){
        regexVerification.isNameValid( name );
        isUserAuthorized( componentId, account );
        Component component = componentRepository.findById( componentId ).orElseThrow();
        component.setName( name );
        componentRepository.save( component );
    }

    public void delete( String componentId, Account account ){
        isUserAuthorized( componentId, account );
        Component component = componentRepository.findById( componentId ).orElseThrow();
        componentRepository.delete( component );
    }

    public Component getById(String componentId ){
        return componentRepository.findById( componentId ).orElseThrow();
    }

    public List<InternalComponentFormat> list(String projectId, Account account ){
        websiteService.isOwner( projectId, account );
        return dataFormattingComponentService.formatSimple(componentRepository.getComponentsByProjectId(projectId));
    }

}
