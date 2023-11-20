package dev.bernouy.cms.feature.website.version;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.version.dto.req.ReqCreateVersionDTO;
import dev.bernouy.cms.feature.website.version.dto.req.ReqUploadFileDTO;
import dev.bernouy.cms.feature.website.version.dto.res.ResVersionDTO;
import dev.bernouy.cms.feature.website.version.service.BusinessVersionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RequestParam String nomParam
@RestController
@RequestMapping("/api/v1/component/version")
public class VersionController {

    private BusinessVersionService versionService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public VersionController(BusinessVersionService versionService, HttpServletResponse response, HttpServletRequest request) {
        this.versionService = versionService;
        this.response = response;
        this.request = request;
    }


    @PostMapping("")
    public ResponseEntity<String> createVersion(@RequestBody ReqCreateVersionDTO dto){
        Account account = (Account) request.getAttribute("account");
        Version version = versionService.create(dto, account);
        return new ResponseEntity<>(version.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{versionID}/uploadJS")
    public ResponseEntity<String> uploadJS(@RequestBody ReqUploadFileDTO dto, @PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.uploadJS(dto, account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{versionID}/uploadCSS")
    public ResponseEntity<String> uploadCSS(@RequestBody ReqUploadFileDTO dto, @PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.uploadCSS(dto, account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{versionID}/deploy")
    public ResponseEntity<String> deployVersion(@PathVariable String versionID){
        Account account = (Account) request.getAttribute("account");
        versionService.deploy(account, versionID);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ResVersionDTO> getList(@RequestParam String componentId){
        Account account = (Account) request.getAttribute("account");
        return versionService.getListVersion(account, componentId);
    }

}
