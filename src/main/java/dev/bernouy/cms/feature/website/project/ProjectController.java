package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.formatting.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.formatting.request.PatchDomainWebsite;
import dev.bernouy.cms.feature.website.project.formatting.response.ProjectFormatting;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private BusinessLogicProjectService projectService;
    private HttpServletResponse response;
    private HttpServletRequest  request;

    @Autowired
    public ProjectController(BusinessLogicProjectService websiteService, HttpServletResponse response, HttpServletRequest request){
        this.projectService = websiteService;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create( @RequestBody ReqCreateWebsiteDTO dto ){
        Account account = (Account) request.getAttribute("account");
        Project website = projectService.create(dto, account);
        return new ResponseEntity<>(website.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/editDomain")
    public ResponseEntity<String> editDomain( @RequestBody PatchDomainWebsite dto ){
        Account account = (Account) request.getAttribute("account");
        projectService.editDomain(dto.getDomain(), dto.getWebsiteID(), account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ProjectFormatting> getList(){
        Account account = (Account) request.getAttribute("account");
        return projectService.getListWebsite(account);
    }

    @GetMapping("/getDetail/{projectId}")
    public Project isAuth(@PathVariable String projectId){
        Account account = (Account) request.getAttribute("account");
        return projectService.getWebsite(projectId, account);
    }
    
}
