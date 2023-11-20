package dev.bernouy.cms.feature.website.layout.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.LayoutRepository;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqCreateLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqPatchDefaultLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqPatchNameLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.res.ResLayoutDTO;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.project.service.PersistentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLayoutService {

    private LayoutRepository layoutRepository;
    private RegexComponent regexComponent;
    private FormattingLayoutService dataFormattingLayoutService;
    private PersistentLayoutService persistentLayoutService;
    private AuthProjectService authProjectService;
    private PersistentProjectService persistentProjectService;

    @Autowired
    public BusinessLayoutService(FormattingLayoutService dataFormattingLayoutService, LayoutRepository repository, RegexComponent regexComponent, PersistentLayoutService persistentLayoutService, AuthProjectService authProjectService, PersistentProjectService persistentProjectService){
        this.layoutRepository = repository;
        this.regexComponent = regexComponent;
        this.dataFormattingLayoutService = dataFormattingLayoutService;
        this.persistentLayoutService = persistentLayoutService;
        this.authProjectService = authProjectService;
        this.persistentProjectService = persistentProjectService;
    }

    public Layout create(ReqCreateLayoutDTO dto, Account account) {
        Project project = persistentProjectService.findById( dto.getProjectId() );
        authProjectService.checkIsOwner(project, account);
        Layout layout = new Layout();
        layout.setProject(project);
        layout.setName(dto.getName());
        persistentLayoutService.save(layout);
        return layout;
    }

    public void delete(String layoutId, Account account) {
        Layout layout = persistentLayoutService.getById(layoutId);
        authProjectService.checkIsOwner(layout.getProject(), account);
        layoutRepository.delete(layout);
    }

    public void patchName(String layoutId, ReqPatchNameLayoutDTO dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Layout layout = persistentLayoutService.getById(layoutId);
        authProjectService.checkIsOwner(layout.getProject(), account);
        layout.setName(dto.getName());
        layoutRepository.save(layout);
    }

    public void patchDefault(String layoutId, ReqPatchDefaultLayoutDTO dto, Account account) {
        Layout layout = persistentLayoutService.getById(layoutId);
        authProjectService.checkIsOwner(layout.getProject(), account);
        Layout lDefault = persistentLayoutService.getDefaultLayout(layout.getProject());
        if (lDefault != null) {
            lDefault.setIsDefault(false);
            persistentLayoutService.save(lDefault);
        }
        layout.setIsDefault(dto.getDefault());
        persistentLayoutService.save(layout);
    }

    public List<ResLayoutDTO> list(String websiteId, Account account) {
        Project project = persistentProjectService.findById(websiteId);
        authProjectService.checkIsOwner(project, account);
        return dataFormattingLayoutService.formatLayouts(persistentLayoutService.getAllByProject(project));
    }

    public ResLayoutDTO getById(String layoutId, Account account) {
        Layout layout = persistentLayoutService.getById(layoutId);
        if (layout == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authProjectService.checkIsOwner(layout.getProject(), account);
        return dataFormattingLayoutService.formatLayout(layout);
    }

}
