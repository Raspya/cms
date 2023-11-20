package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.ComponentRepository;
import dev.bernouy.cms.feature.website.component.dto.res.ResComponentDTO_VInternalComponent;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.project.service.PersistentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessComponentService {

    private ComponentRepository componentRepository;
    private RegexComponent regexVerification;
    private FormattingComponentService dataFormattingComponentService;
    private PersistentComponentService persistentComponentService;
    private AuthProjectService authProjectService;
    private PersistentProjectService dataPersistentProjectService;
    private AuthComponentService authComponentService;

    @Autowired
    public BusinessComponentService(ComponentRepository componentRepository, RegexComponent regexVerification, FormattingComponentService dataFormattingComponentService, PersistentComponentService persistentComponentService, AuthProjectService authProjectService, PersistentProjectService dataPersistentProjectService, AuthComponentService authComponentService){
        this.componentRepository = componentRepository;
        this.regexVerification = regexVerification;
        this.dataFormattingComponentService = dataFormattingComponentService;
        this.persistentComponentService = persistentComponentService;
        this.authProjectService = authProjectService;
        this.dataPersistentProjectService = dataPersistentProjectService;
        this.authComponentService = authComponentService;
    }

    public Component create(String name, String websiteId, Account account){
        regexVerification.isNameValid(name);
        Project website = dataPersistentProjectService.findById(websiteId);
        authProjectService.checkIsOwner(website, account);
        Component component = new Component();
        component.setName(name);
        component.setProject(website);
        persistentComponentService.save(component);
        return component;
    }

    public void patchName(String name, String componentId, Account account){
        regexVerification.isNameValid(name);
        Component component = persistentComponentService.getById( componentId );
        authComponentService.checkUserAuth(component, account);
        component.setName( name );
        persistentComponentService.save(component);
    }

    public void delete(String componentId, Account account){
        Component component = persistentComponentService.getById(componentId);
        authComponentService.checkUserAuth(component, account);
        componentRepository.delete( component );
    }

    public List<ResComponentDTO_VInternalComponent> listProjectComponents(String projectId, Account account){
        Project project = dataPersistentProjectService.findById(projectId);
        authProjectService.checkIsOwner(project, account);
        return dataFormattingComponentService.formatSimple(persistentComponentService.getComponentsByProjectId(projectId));
    }

}
