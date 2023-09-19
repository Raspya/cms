package dev.bernouy.cms.feature.website.project;

import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.dto.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.dto.request.ReqEditDomainWebsiteDTO;
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

    private ProjectService websiteService;
    private HttpServletResponse response;
    private HttpServletRequest  request;

    @Autowired
    public ProjectController(ProjectService websiteService, HttpServletResponse response, HttpServletRequest request){
        this.websiteService = websiteService;
        this.response = response;
        this.request = request;
    }

    @PostMapping("create")
    public ResponseEntity<String> create( @RequestBody ReqCreateWebsiteDTO dto ){
        Account account = (Account) request.getAttribute("account");
        Project website = websiteService.create(dto.getName(), account);
        return new ResponseEntity<>(website.getId(), HttpStatus.CREATED);
    }

    @PostMapping("editDomain")
    public ResponseEntity<String> editDomain( @RequestBody ReqEditDomainWebsiteDTO dto ){
        Account account = (Account) request.getAttribute("account");
        websiteService.editDomain(dto.getDomain(), dto.getWebsiteID(), account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("list")
    public List<Project> getList(){
        Account account = (Account) request.getAttribute("account");
        return websiteService.getListWebsite(account);
    }


}
