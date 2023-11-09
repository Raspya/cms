package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectRepository;
import dev.bernouy.cms.feature.website.project.formatting.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.formatting.response.ProjectFormatting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogicProjectService {

    private ProjectRepository websiteRepository;
    private DataFormattingProjectService dataFormattingProjectService;
    private RegexComponent regexComponent;
    private DataPersistentProjectService dataPersistentProjectService;

    @Autowired
    public BusinessLogicProjectService(ProjectRepository websiteRepository, RegexComponent regexVerification, DataFormattingProjectService dataFormattingProjectService, DataPersistentProjectService dataPersistentProjectService){
        this.websiteRepository = websiteRepository;
        this.regexComponent = regexVerification;
        this.dataFormattingProjectService = dataFormattingProjectService;
        this.dataPersistentProjectService = dataPersistentProjectService;
    }

    public Project create(ReqCreateWebsiteDTO dto, Account account ){
        regexComponent.isNameValid(dto.getName());
        regexComponent.isDomainValid(dto.getDomain());
        if ( dataPersistentProjectService.isDomainAlreadyExist(dto.getDomain()) )
            throw new BasicException("Domain already exist", HttpStatus.BAD_REQUEST);
        Project website = new Project();
        website.setName(dto.getName());
        website.setOwner(account);
        website.setDomain(dto.getDomain());
        websiteRepository.save(website);
        return website;
    }

    public void editDomain( String domain, String websiteID, Account account ){
        regexComponent.isDomainValid(domain);
        if ( dataPersistentProjectService.isDomainAlreadyExist(domain) )
            throw new BasicException("Domain already exist", HttpStatus.BAD_REQUEST);
        isOwner(websiteID, account);
        Project website = websiteRepository.findById(websiteID).orElseThrow();
        website.setDomain(domain);
        websiteRepository.save(website);
    }

    public Project getWebsite(String websiteID ){
        return websiteRepository.findById(websiteID).orElseThrow();
    }

    public Project getWebsite(String websiteId, Account account){
        isOwner(websiteId, account);
        return getWebsite(websiteId);
    }

    public List<ProjectFormatting> getListWebsite(Account account ){
        return dataFormattingProjectService.formatProject(websiteRepository.getProjectsByOwner(account));
    }

    public BusinessLogicProjectService isOwner(String websiteID, Account account ){
        Project website;
        try{
            website = websiteRepository.findById(websiteID).orElseThrow();
        } catch ( Exception e ){ throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN); }
        if ( !website.getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
        return this;
    }

}
