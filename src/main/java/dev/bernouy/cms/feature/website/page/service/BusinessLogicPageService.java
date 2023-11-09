package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.layout.service.BusinessLogicLayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.PageRepository;
import dev.bernouy.cms.feature.website.page.dto.request.*;
import dev.bernouy.cms.feature.website.page.dto.response.PageFormatting;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogicPageService {

    private PageRepository pageRepository;
    private RegexComponent regexComponent;
    private BusinessLogicLayoutService businessLogicLayoutService;
    private BusinessLogicProjectService projectService;
    private DataFormattingPageService dataFormattingPageService;


    @Autowired
    public BusinessLogicPageService(DataFormattingPageService dataFormattingPageService,PageRepository repository, RegexComponent regexComponent, BusinessLogicLayoutService businessLogicLayoutService, BusinessLogicProjectService projectService){
        this.pageRepository = repository;
        this.regexComponent = regexComponent;
        this.businessLogicLayoutService = businessLogicLayoutService;
        this.projectService = projectService;
        this.dataFormattingPageService = dataFormattingPageService;
    }

    public Page create(ReqCreatePage dto, Account account) {
        projectService.isOwner(dto.getProjectId(), account);
        Project project = projectService.getWebsite( dto.getProjectId() );
        Page page = new Page();
        page.setProject(project);
        page.setName(dto.getName());
        if (pageRepository.findByUrl(dto.getPath()) != null) throw new BasicException(WebsiteExceptionMessages.INVALID_PAGE_URL);
        page.setUrl(dto.getPath());
        page.setPublished(false);
        page.setLayout(businessLogicLayoutService.getLayoutDefault(account, project));
        pageRepository.save(page);
        return page;
    }

    public void delete(String pageId, Account account) {
        Page page = getById(pageId, account);
        pageRepository.delete(page);
    }

    public void setName(String pageId, ReqSetNamePage dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Page page = getById(pageId, account);
        page.setName(dto.getName());
        pageRepository.save(page);
    }

    public void setDeploy(String pageId, ReqSetDeployPage dto, Account account) {
        Page page = getById(pageId, account);
        if (page.getTitle() == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PAGE_TITLE);
        page.setPublished(dto.getDeploy());
        pageRepository.save(page);
    }

    public void setUrl(String pageId, ReqSetUrlPage dto, Account account) {
        if (pageRepository.findByUrl(dto.getPath()) != null) throw new BasicException(WebsiteExceptionMessages.INVALID_PAGE_URL);
        Page page = getById(pageId, account);
        page.setUrl(dto.getPath());
        pageRepository.save(page);
    }

    public void setTitle(String pageId, ReqSetTitlePage dto, Account account) {
        Page page = getById(pageId, account);
        System.out.println(page.getTitle());
        pageRepository.save(page);
    }

    public void setDescription(String pageId, ReqSetDescriptionPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setDescription(dto.getDescription());
        pageRepository.save(page);
    }

    public void setLayout(String pageId, ReqSetLayoutPage dto, Account account) {
        Page page = getById(pageId, account);
        page.setLayout(businessLogicLayoutService.getById(dto.getLayoutId(), account));
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


    public List<PageFormatting> list(String websiteId, Account account) {
        return dataFormattingPageService.formatPages(pageRepository.findAllByProjectId(websiteId));
    }
}
