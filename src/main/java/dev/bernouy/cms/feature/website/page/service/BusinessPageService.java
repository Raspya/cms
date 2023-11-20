package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.service.PersistentLayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.req.*;
import dev.bernouy.cms.feature.website.page.dto.res.ResPageDTO;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.project.service.PersistentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessPageService {

    private RegexComponent regexComponent;
    private FormattingPageService dataFormattingPageService;
    private AuthProjectService authProjectService;
    private PersistentProjectService persistentProjectService;
    private PersistentPageService persistentPageService;
    private PersistentLayoutService persistentLayoutService;


    @Autowired
    public BusinessPageService(FormattingPageService dataFormattingPageService, RegexComponent regexComponent, AuthProjectService authProjectService, PersistentProjectService persistentProjectService, PersistentPageService persistentPageService, PersistentLayoutService persistentLayoutService){
        this.regexComponent = regexComponent;
        this.dataFormattingPageService = dataFormattingPageService;
        this.authProjectService = authProjectService;
        this.persistentProjectService = persistentProjectService;
        this.persistentPageService = persistentPageService;
        this.persistentLayoutService = persistentLayoutService;
    }

    public Page create(ReqCreatePage dto, Account account) {
        regexComponent.isPath(dto.getPath());
        Project website = persistentProjectService.findById(dto.getProjectId());
        authProjectService.checkIsOwner(website, account);
        if (persistentPageService.getByPathAndProject(dto.getPath(), website) != null)
            throw new BasicException("Page with this path already exists");
        Page page = new Page();
        page.setProject(website);
        page.setName(dto.getName());
        page.setPath(dto.getPath());
        page.setPublished(false);
        persistentPageService.save(page);
        return page;
    }

    public void delete(String pageId, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        persistentPageService.delete(page);
    }

    public void patchName(String pageId, ReqPatchNamePageDTO dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        page.setName(dto.getName());
        persistentPageService.save(page);
    }

    public void patchDeploy(String pageId, ReqPatchDeployPageDTO dto, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        if (page.getTitle() == null) throw new BasicException("Page title is not set");
        page.setPublished(dto.getDeploy());
        persistentPageService.save(page);
    }

    public void patchPath(String pageId, ReqPatchPathPageDTO dto, Account account) {
        regexComponent.isPath(dto.getPath());
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        if (persistentPageService.getByPathAndProject(dto.getPath(), page.getProject()) != null) throw new BasicException(WebsiteExceptionMessages.INVALID_PAGE_URL);
        page.setPath(dto.getPath());
        persistentPageService.save(page);
    }

    public void patchTitle(String pageId, ReqPatchTitlePageDTO dto, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        page.setTitle(dto.getTitle());
        persistentPageService.save(page);
    }

    public void patchDescription(String pageId, ReqPatchDescriptionPageDTO dto, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        page.setDescription(dto.getDescription());
        persistentPageService.save(page);
    }

    public void patchLayout(String pageId, ReqPatchLayoutPageDTO dto, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        Layout layout = persistentLayoutService.getById(dto.getLayoutId());
        if ( layout.getProject() != page.getProject() ) throw new BasicException("Layout does not belong to this website");
        page.setLayout(layout);
        persistentPageService.save(page);
    }

    public List<ResPageDTO> list(String websiteId, Account account) {
        Project website = persistentProjectService.findById(websiteId);
        authProjectService.checkIsOwner(website, account);
        return dataFormattingPageService.formatPages(persistentPageService.getProjectsByWebsite(website));
    }

    public ResPageDTO detail(String pageId, Account account) {
        Page page = persistentPageService.getById(pageId);
        authProjectService.checkIsOwner(page.getProject(), account);
        return dataFormattingPageService.formatPage(page);
    }
}
