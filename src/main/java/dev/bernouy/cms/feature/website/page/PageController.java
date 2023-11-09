package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.layout.formatting.response.LayoutFormatting;
import dev.bernouy.cms.feature.website.page.dto.request.*;
import dev.bernouy.cms.feature.website.page.dto.response.PageFormatting;
import dev.bernouy.cms.feature.website.page.service.BusinessLogicPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {

    private BusinessLogicPageService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public PageController(BusinessLogicPageService service, HttpServletResponse response, HttpServletRequest request) {
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
    public ResponseEntity<String> setName(@PathVariable String pageId, @RequestBody ReqSetNamePage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setName(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/deploy")
    public ResponseEntity<String> setDeploy(@PathVariable String pageId, @RequestBody ReqSetDeployPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setDeploy(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/path")
    public ResponseEntity<String> setUrl(@PathVariable String pageId, @RequestBody ReqSetUrlPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setUrl(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/title")
    public ResponseEntity<String> setTitle(@PathVariable String pageId, @RequestBody ReqSetTitlePage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setTitle(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/description")
    public ResponseEntity<String> setDescription(@PathVariable String pageId, @RequestBody ReqSetDescriptionPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setDescription(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{pageId}/layout")
    public ResponseEntity<String> setLayout(@PathVariable String pageId, @RequestBody ReqSetLayoutPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setLayout(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @GetMapping("/{pageId}")
    public Page get(@PathVariable String pageId) {
        Account account = (Account) request.getAttribute("account");
        return service.getById(pageId, account);
    }

    @GetMapping("/list")
    public List<PageFormatting> list(@RequestParam String websiteId) {
        Account account = (Account) request.getAttribute("account");
        return service.list(websiteId, account );
    }


    
}
