package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.LibraryService;
import dev.bernouy.cms.feature.website.library.dto.ReqAddRemoveVersionLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqNameLibrary;
import dev.bernouy.cms.feature.website.page.dto.*;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {

    private PageService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public PageController(PageService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPage(@RequestBody ReqCreatePage dto) {
        Account account = (Account) request.getAttribute("account");
        Page page = service.create(dto, account);
        return new ResponseEntity<>(page.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/delete")
    public ResponseEntity<String> delete(@PathVariable String pageId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(pageId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setName")
    public ResponseEntity<String> setName(@PathVariable String pageId, @RequestBody ReqSetNamePage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setName(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setDeploy")
    public ResponseEntity<String> setDeploy(@PathVariable String pageId, @RequestBody ReqSetDeployPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setDeploy(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setUrl")
    public ResponseEntity<String> setUrl(@PathVariable String pageId, @RequestBody ReqSetUrlPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setUrl(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setTitle")
    public ResponseEntity<String> setTitle(@PathVariable String pageId, @RequestBody ReqSetTitlePage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setTitle(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setDescription")
    public ResponseEntity<String> setDescription(@PathVariable String pageId, @RequestBody ReqSetDescriptionPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setDescription(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{pageId}/setLayout")
    public ResponseEntity<String> setLayout(@PathVariable String pageId, @RequestBody ReqSetLayoutPage dto) {
        Account account = (Account) request.getAttribute("account");
        service.setLayout(pageId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @GetMapping("/{pageId}/list")
    public Page list(@PathVariable String pageId) {
        Account account = (Account) request.getAttribute("account");
        return service.getById(pageId, account);
    }


    
}
