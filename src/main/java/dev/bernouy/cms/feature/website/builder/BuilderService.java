package dev.bernouy.cms.feature.website.builder;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.dto.ReqCreateBuilder;
import dev.bernouy.cms.feature.website.builder.dto.ReqPositionBuilder;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.LayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.PageService;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuilderService {

    private BuilderRepository builderRepository;
    private RegexComponent regexComponent;
    private VersionService versionService;
    private PageService pageService;
    private LayoutService layoutService;

    @Autowired
    public BuilderService(BuilderRepository builderRepository, RegexComponent regexComponent, VersionService versionService, PageService pageService, LayoutService layoutService) {
        this.builderRepository = builderRepository;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.pageService = pageService;
        this.layoutService = layoutService;
    }

    public Builder create(ReqCreateBuilder dto, Account account) {
        versionService.getByIdAccount(dto.getVersionId(), account);
        if (!(dto.getLayoutId() == null && dto.getPageId() != null) && !(dto.getLayoutId() != null && dto.getPageId() == null))
            throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_PAGEORLAYOUT);

        Version version = versionService.getById(dto.getVersionId());
        Page page = pageService.getById(dto.getPageId(), account);
        Layout layout = layoutService.getById(dto.getLayoutId(), account);

        Builder builder = new Builder();
        builder.setPage(page);
        builder.setLayout(layout);
        builder.setComponentVersion(version);

        Integer maxPos = builderRepository.findFirstByComponentVersionOrderByPositionDesc(version.getId()).getPosition();
        if (maxPos == null) maxPos = 0;
        builder.setPosition(maxPos+1);
        builderRepository.save(builder);
        return builder;
    }

    public void delete(String builderId, Account account) {
        Builder builder = getById(builderId, account);
        builderRepository.delete(builder);
    }

    public void setPosition(ReqPositionBuilder dto, String builderId,Account account) {
        if (dto.getPosition() < 1) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        Builder builder = getById(builderId, account);
        Integer maxPos = builderRepository.findFirstByComponentVersionOrderByPositionDesc(builder.getComponentVersion().getId()).getPosition();

        if (dto.getPosition() > maxPos) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        List<Builder> builderList = builderRepository.findAllByComponentVersionIdOrderByPositionAsc(builder.getComponentVersion().getId());
        List<Builder> toUpdate = new ArrayList<>();
        int i, min, max;
        if ( builder.getPosition() > dto.getPosition() ) {
            i = 1;
            min = dto.getPosition();
            max = builder.getPosition();
        }
        else {
            i = -1;
            min = builder.getPosition();
            max = dto.getPosition();
        }
        for ( int j = min ; j < max ; j++ ){
            builderList.get(j-1).setPosition(builderList.get(j-1).getPosition()+i);
            toUpdate.add(builderList.get(j-1));
        }
        builder.setPosition(dto.getPosition());
        toUpdate.add(builder);

        builderRepository.saveAll(toUpdate);
    }


    public Builder getById(String builderId, Account account) {
        Builder builder = builderRepository.findById(builderId).orElse(null);
        if (builder == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(builderId, account);
        return builder;
    }

    private void authorizeAccount(String builderId, Account account) {
        Builder builder = builderRepository.findById(builderId).orElse(null);
        if (builder == null || !builder.getComponentVersion().getComponent().getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }
}
