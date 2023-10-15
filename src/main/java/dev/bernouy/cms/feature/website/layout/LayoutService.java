package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.layout.dto.ReqCreateLayout;
import dev.bernouy.cms.feature.website.layout.dto.ReqSetDefaultLayout;
import dev.bernouy.cms.feature.website.layout.dto.ReqSetNameLayout;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutService {

    private LayoutRepository layoutRepository;
    private RegexComponent regexComponent;
    private ProjectService projectService;


    @Autowired
    public LayoutService(LayoutRepository repository, RegexComponent regexComponent, ProjectService projectService){
        this.layoutRepository = repository;
        this.regexComponent = regexComponent;
        this.projectService = projectService;
    }

    public Layout create(ReqCreateLayout dto, Account account) {
        projectService.isOwner(dto.getProjectId(), account);
        Project project = projectService.getWebsite( dto.getProjectId() );
        Layout layout = new Layout();
        layout.setProject(project);
        layout.setName(dto.getName());
        layoutRepository.save(layout);
        return layout;
    }

    public void delete(String layoutId, Account account) {
        Layout layout = getById(layoutId, account);
        layoutRepository.delete(layout);
    }

    public void setName(String layoutId, ReqSetNameLayout dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Layout layout = getById(layoutId, account);
        layout.setName(dto.getName());
        layoutRepository.save(layout);
    }

    public void setDefault(String layoutId, ReqSetDefaultLayout dto, Account account) {
        Layout layout = getById(layoutId, account);
        Layout layoutDefault = getLayoutDefault(account, layout.getProject());
        if (layoutDefault != null) {
            layoutDefault.setaBoolean(false);
            layoutRepository.save(layoutDefault);
        }
        layout.setaBoolean(dto.getDefault());
        layoutRepository.save(layout);
    }

    public Layout getById(String layoutId, Account account) {
        Layout layout = layoutRepository.findById(layoutId).orElse(null);
        if (layout == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(layoutId, account);
        return layout;
    }

    private void authorizeAccount(String layoutId, Account account) {
        Layout layout = layoutRepository.findById(layoutId).orElse(null);
        if (layout == null || !layout.getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

    public Layout getLayoutDefault(Account account, Project project) {
        List<Layout> lstLayout = layoutRepository.findAllByProject(project);
        for (Layout l : lstLayout) {
            if (l.isaBoolean()) {
                authorizeAccount(l.getId(), account);
                return l;
            }
        }
        return null;
    }
}
