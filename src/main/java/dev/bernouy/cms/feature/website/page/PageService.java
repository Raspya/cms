package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.layout.LayoutService;
import dev.bernouy.cms.feature.website.page.dto.*;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private PageRepository pageRepository;
    private RegexComponent regexComponent;
    private LayoutService layoutService;
    private ProjectService projectService;


    @Autowired
    public PageService(PageRepository repository, RegexComponent regexComponent, LayoutService layoutService, ProjectService projectService){
        this.pageRepository = repository;
        this.regexComponent = regexComponent;
        this.layoutService = layoutService;
        this.projectService = projectService;
    }

    public Page create(ReqCreatePage dto, Account account) {
        projectService.isOwner(dto.getProjectId(), account);
        Project project = projectService.getWebsite( dto.getProjectId() );
        Page page = new Page();
        page.setProject(project);
        page.setName(dto.getName());
        pageRepository.save(page);
        return page;
    }

    public void delete(String pageId, Account account) {
        Page page = getById(pageId, account);
        pageRepository.delete(page);
    }

    public void setName( String pageId, ReqSetNamePage dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Page page = getById(pageId, account);
        page.setName(dto.getName());
        pageRepository.save(page);
    }

    public void setDeploy(String pageId, ReqSetDeployPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setPublished(dto.getDeploy());
        pageRepository.save(page);
    }

    public void setUrl(String pageId, ReqSetUrlPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setUrl(dto.getUrl());
        pageRepository.save(page);
    }

    public void setTitle(String pageId, ReqSetTitlePage dto, Account account) {
        Page page = getById(pageId, account);
        page.setTitle(dto.getTitle());
        pageRepository.save(page);
    }

    public void setDescription(String pageId, ReqSetDescriptionPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setTitle(dto.getDescription());
        pageRepository.save(page);
    }

    public void setLayout(String pageId, ReqSetLayoutPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setLayout(layoutService.getById(dto.getLayoutId(), account));
        pageRepository.save(page);
    }

    public Page getById(String layoutId, Account account) {
        Page page = pageRepository.findById(layoutId).orElse(null);
        if (page == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(layoutId, account);
        return page;
    }

    private void authorizeAccount(String layoutId, Account account) {
        Page page = pageRepository.findById(layoutId).orElse(null);
        if (page == null || !page.getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }


}
