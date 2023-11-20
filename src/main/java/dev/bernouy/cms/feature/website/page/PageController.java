package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.page.dto.req.*;
import dev.bernouy.cms.feature.website.page.dto.res.ResPageDTO;
import dev.bernouy.cms.feature.website.page.service.BusinessPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {

    private BusinessPageService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public PageController(BusinessPageService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> createPage(@RequestBody ReqCreatePage dto) {
        Account account = (Account) request.getAttribute("account");
        Page page = service.create(dto, account);
        return new ResponseEntity<>(page.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<String> delete(@PathVariable String pageId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(pageId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/name")
    public ResponseEntity<String> setName(@PathVariable String pageId, @RequestBody ReqPatchNamePageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchName(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/deploy")
    public ResponseEntity<String> setDeploy(@PathVariable String pageId, @RequestBody ReqPatchDeployPageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchDeploy(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/path")
    public ResponseEntity<String> setUrl(@PathVariable String pageId, @RequestBody ReqPatchPathPageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchPath(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/title")
    public ResponseEntity<String> setTitle(@PathVariable String pageId, @RequestBody ReqPatchTitlePageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchTitle(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/description")
    public ResponseEntity<String> setDescription(@PathVariable String pageId, @RequestBody ReqPatchDescriptionPageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchDescription(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/layout")
    public ResponseEntity<String> setLayout(@PathVariable String pageId, @RequestBody ReqPatchLayoutPageDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchLayout(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @GetMapping("/{pageId}")
    public ResPageDTO get(@PathVariable String pageId) {
        Account account = (Account) request.getAttribute("account");
        return service.detail(pageId, account);
    }

    @GetMapping("/list")
    public List<ResPageDTO> list(@RequestParam String websiteId) {
        Account account = (Account) request.getAttribute("account");
        return service.list(websiteId, account );
    }


    
}
