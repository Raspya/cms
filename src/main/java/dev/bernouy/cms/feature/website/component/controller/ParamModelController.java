package dev.bernouy.cms.feature.website.component.controller;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqInfoParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqOptionParamModel;
import dev.bernouy.cms.feature.website.component.model.ParamModel;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.repository.ParamModelRepository;
import dev.bernouy.cms.feature.website.component.service.ParamModelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/component/version/param")
public class ParamModelController {

    private ParamModelService paramModelService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamModelController(ParamModelService paramModelService, HttpServletRequest request, HttpServletResponse response){
        this.paramModelService = paramModelService;
        this.request = request;
        this.response = response;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamModel dto) {
        Account account = (Account) request.getAttribute("account");
        ParamModel paramModel = paramModelService.create(dto, account);
        return new ResponseEntity<>(paramModel.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/delete")
    public ResponseEntity<String> delete(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.delete(account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/setKey")
    public ResponseEntity<String> setKey(@RequestBody ReqInfoParamModel dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.setKey(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/setName")
    public ResponseEntity<String> setName(@RequestBody ReqInfoParamModel dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.setName(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/setPosition")
    public ResponseEntity<String> setPosition(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.setPosition(account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/setOption")
    public ResponseEntity<String> setOption(@RequestBody ReqOptionParamModel dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.setOption(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{paramModelId}/resetOption")
    public ResponseEntity<String> resetOption(@RequestBody ReqInfoParamModel dto, @PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.resetOption(dto, account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @PostMapping("/{paramModelId}/resetOptions")
    public ResponseEntity<String> resetOptions(@PathVariable String paramModelId) {
        Account account = (Account) request.getAttribute("account");
        paramModelService.resetOptions(account, paramModelId);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
