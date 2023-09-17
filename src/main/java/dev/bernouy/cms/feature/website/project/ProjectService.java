package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository websiteRepository;
    private RegexComponent regexComponent;

    @Autowired
    public ProjectService(ProjectRepository websiteRepository, RegexComponent regexVerification){
        this.websiteRepository = websiteRepository;
        this.regexComponent = regexVerification;
    }

    public Project create(String name, Account account ){
        regexComponent.isNameValid(name);
        Project website = new Project();
        website.setName(name);
        website.setOwner(account);
        websiteRepository.save(website);
        return website;
    }

    public void editDomain( String domain, String websiteID, Account account ){
        regexComponent.isDomainValid(domain);
        Project website = websiteRepository.findById(websiteID).orElseThrow();
        if ( !website.getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
        website.setDomain(domain);
        websiteRepository.save(website);
    }

    public Project getWebsite(String websiteID ){
        return websiteRepository.findById(websiteID).orElseThrow();
    }

    public List<Project> getListWebsite(Account account ){
        return websiteRepository.getProjectsByOwner(account);
    }

    public ProjectService isOwner(String websiteID, Account account ){
        Project website;
        try{
            website = websiteRepository.findById(websiteID).orElseThrow();
        } catch ( Exception e ){ throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN); }
        if ( !website.getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
        return this;
    }

}
