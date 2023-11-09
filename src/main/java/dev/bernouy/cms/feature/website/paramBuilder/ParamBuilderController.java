package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.page.dto.response.PageFormatting;
import dev.bernouy.cms.feature.website.paramBuilder.dto.request.ReqCreateParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.request.ReqSetValueParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.response.ParamBuilderFormatting;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessLogicParamBuilderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamBuilder dto) {
        Account account = (Account) request.getAttribute("account");
        ParamBuilder paramBuilder = service.create(dto, account);
        return new ResponseEntity<>(paramBuilder.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{paramBuilderId}/value")
    public ResponseEntity<String> setValue(@PathVariable String paramBuilderId, @RequestBody ReqSetValueParamBuilder dto) {
        Account account = (Account) request.getAttribute("account");
        service.setValue(paramBuilderId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ParamBuilderFormatting> list(@RequestParam(required = false) String builderId, @RequestParam(required = false) String paramBuilderId ) {
        Account account = (Account) request.getAttribute("account");
        return service.listAllParamBuilder(builderId, paramBuilderId, account );
    }
    
}
