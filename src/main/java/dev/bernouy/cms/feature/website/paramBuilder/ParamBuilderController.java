package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqCreateParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqPatchValueParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.dto.res.ResParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessParamBuilderService;
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

    private BusinessParamBuilderService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamBuilderController(BusinessParamBuilderService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamBuilderDTO dto) {
        Account account = (Account) request.getAttribute("account");
        ParamBuilder paramBuilder = service.create(dto, account);
        return new ResponseEntity<>(paramBuilder.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{paramBuilderId}/value")
    public ResponseEntity<String> setValue(@PathVariable String paramBuilderId, @RequestBody ReqPatchValueParamBuilderDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchValue(paramBuilderId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ResParamBuilderDTO> list(@RequestParam(required = false) String builderId, @RequestParam(required = false) String paramBuilderId ) {
        Account account = (Account) request.getAttribute("account");
        return service.listAllParamBuilder(builderId, paramBuilderId, account );
    }
    
}
