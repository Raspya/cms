package dev.bernouy.cms.feature.website.component.controller;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModel;
import dev.bernouy.cms.feature.website.component.service.VersionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// RequestParam String nomParam
@RestController
@RequestMapping("/api/v1/component/version")
public class VersionController {

    private VersionService versionService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public VersionController(VersionService versionService, HttpServletResponse response, HttpServletRequest request) {
        this.versionService = versionService;
        this.response = response;
        this.request = request;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createVersion(@RequestBody ReqCreateVersion dto){
        Account account = (Account) request.getAttribute("account");
        Version version = versionService.create(dto, account);
        return new ResponseEntity<>(version.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{versionID}/uploadJS")
    public ResponseEntity<String> uploadJS(@RequestBody ReqUploadFile dto, @PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.uploadJS(dto, account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{versionID}/uploadCSS")
    public ResponseEntity<String> uploadCSS(@RequestBody ReqUploadFile dto, @PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.uploadCSS(dto, account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{versionID}/deploy")
    public ResponseEntity<String> deployVersion(@PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.deploy(account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{versionID}/get")
    public Version get(@PathVariable String versionID) {
        Account account = (Account) request.getAttribute("account");
        return versionService.getByIdAccount(versionID, account);
    }

}
