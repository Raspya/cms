package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.dto.req.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.dto.res.ResProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessProjectService {

    private FormattingProjectService dataFormattingProjectService;
    private RegexComponent regexComponent;
    private PersistentProjectService dataPersistentProjectService;
    private AuthProjectService authProjectService;

    @Autowired
    public BusinessProjectService(RegexComponent regexVerification, FormattingProjectService dataFormattingProjectService, PersistentProjectService dataPersistentProjectService, AuthProjectService authProjectService){
        this.regexComponent = regexVerification;
        this.dataFormattingProjectService = dataFormattingProjectService;
        this.dataPersistentProjectService = dataPersistentProjectService;
        this.authProjectService = authProjectService;
    }

    public Project create(ReqCreateWebsiteDTO dto, Account account ){
        regexComponent.isNameValid(dto.getName()).isDomainValid(dto.getDomain());
        if ( dataPersistentProjectService.isDomainAlreadyExist(dto.getDomain()) )
            throw new BasicException("Domain already exist", HttpStatus.BAD_REQUEST);
        Project website = new Project();
        website.setName(dto.getName());
        website.setOwner(account);
        website.setDomain(dto.getDomain());
        dataPersistentProjectService.save(website);
        return website;
    }

    public void patchDomain(String domain, String websiteID, Account account ){
        regexComponent.isDomainValid(domain);
        if ( dataPersistentProjectService.isDomainAlreadyExist(domain) )
            throw new BasicException("Domain already exist", HttpStatus.BAD_REQUEST);
        Project website = dataPersistentProjectService.findById(websiteID);
        authProjectService.checkIsOwner(website, account);
        website.setDomain(domain);
        dataPersistentProjectService.save(website);
    }

    public ResProjectDTO getWebsite(String websiteId, Account account){
        Project website = dataPersistentProjectService.findById(websiteId);
        authProjectService.checkIsOwner(website, account);
        return dataFormattingProjectService.formatProject(dataPersistentProjectService.findById(websiteId));
    }

    public List<ResProjectDTO> getListWebsite(Account account ){
        return dataFormattingProjectService.formatProject(dataPersistentProjectService.getProjectsByOwner(account));
    }

}
