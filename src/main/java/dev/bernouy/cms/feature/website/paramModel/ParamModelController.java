package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.paramModel.dto.req.*;
import dev.bernouy.cms.feature.website.paramModel.dto.response.ResParamModelDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.BusinessParamModelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/component/param")
public class ParamModelController {

    private BusinessParamModelService paramModelService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamModelController(BusinessParamModelService paramModelService, HttpServletRequest request, HttpServletResponse response){
        this.paramModelService = paramModelService;
        this.request = request;
        this.response = response;
    }

    @PostMapping("")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamModelDTO dto) {
        Account account = (Account) request.getAttribute("account");
        ParamModel paramModel = paramModelService.create(dto, account);
        return new ResponseEntity<>(paramModel.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{paramModelId}")
    public ResponseEntity<String> delete(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.delete(account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/key")
    public ResponseEntity<String> setKey(@RequestBody ReqKeyParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.patchKey(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/name")
    public ResponseEntity<String> setName(@RequestBody ReqNameParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.patchName(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/position")
    public ResponseEntity<String> setPosition(@RequestBody ReqPositionParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.patchPosition(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping ("/{paramModelId}/option")
    public ResponseEntity<String> setOption(@RequestBody ReqOptionParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.patchOption(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/resetOption")
    public ResponseEntity<String> resetOption(@RequestBody ReqKeyParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.resetOption(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/resetOptions")
    public ResponseEntity<String> resetOptions(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.resetOptions(account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{paramModelId}/value")
    public ResponseEntity<String> setValue(@RequestBody ReqValueParamModelDTO dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.patchValue(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{paramModelId}")
    public ResParamModelDTO get(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        return paramModelService.getById(paramModelId, account);
    }

    @GetMapping("/list")
    public List<ResParamModelDTO> list(@RequestParam(required = false) String paramModelId, @RequestParam String versionId) {
        Account account = (Account) request.getAttribute("account");
        return paramModelService.list(account, paramModelId, versionId);
    }


}
