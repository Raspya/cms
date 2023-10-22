package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.paramBuilder.dto.ReqCreateParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessLogicParamBuilderService;
import dev.bernouy.cms.feature.website.paramModel.dto.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paramBuilder")
public class ParamBuilderController {

    private BusinessLogicParamBuilderService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamBuilderController(BusinessLogicParamBuilderService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamBuilder dto) {
        Account account = (Account) request.getAttribute("account");
        ParamBuilder paramBuilder = service.create(dto, account);
        return new ResponseEntity<>(paramBuilder.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{paramBuilderId}/setValue")
    public ResponseEntity<String> setValue(@PathVariable String paramBuilderId, @RequestBody ReqCreateParamBuilder dto) {
        Account account = (Account) request.getAttribute("account");
        service.setValue(paramBuilderId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
