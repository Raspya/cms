package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.dto.req.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.dto.req.ReqPatchDomainWebsiteDTO;
import dev.bernouy.cms.feature.website.project.dto.res.ResProjectDTO;
import dev.bernouy.cms.feature.website.project.service.BusinessProjectService;
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

    private BusinessProjectService projectService;
    private HttpServletResponse response;
    private HttpServletRequest  request;

    @Autowired
    public ProjectController(BusinessProjectService websiteService, HttpServletResponse response, HttpServletRequest request){
        this.projectService = websiteService;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> create( @RequestBody ReqCreateWebsiteDTO dto ){
        Account account = (Account) request.getAttribute("account");
        Project website = projectService.create(dto, account);
        return new ResponseEntity<>(website.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/domain")
    public ResponseEntity<String> editDomain( @RequestBody ReqPatchDomainWebsiteDTO dto ){
        Account account = (Account) request.getAttribute("account");
        projectService.patchDomain(dto.getDomain(), dto.getWebsiteId(), account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ResProjectDTO> getList(){
        Account account = (Account) request.getAttribute("account");
        return projectService.getListWebsite(account);
    }

    @GetMapping("/{projectId}")
    public ResProjectDTO isAuth(@PathVariable String projectId){
        Account account = (Account) request.getAttribute("account");
        return projectService.getWebsite(projectId, account);
    }

}
