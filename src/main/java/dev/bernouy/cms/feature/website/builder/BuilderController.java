package dev.bernouy.cms.feature.website.builder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.builder.dto.ReqCreateBuilder;
import dev.bernouy.cms.feature.website.builder.dto.ReqPositionBuilder;
import dev.bernouy.cms.feature.website.builder.service.BusinessLogicBuilderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/builder")
public class BuilderController {

    private BusinessLogicBuilderService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public BuilderController(BusinessLogicBuilderService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBuilder(@RequestBody ReqCreateBuilder dto) {
        Account account = (Account) request.getAttribute("account");
        Builder builder = service.create(dto, account);
        return new ResponseEntity<>(builder.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{builderId}/delete")
    public ResponseEntity<String> deleteBuilder(@PathVariable String builderId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(builderId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{builderId}/setPosition")
    public ResponseEntity<String> deleteBuilder(@RequestBody ReqPositionBuilder dto, @PathVariable String builderId) {
        Account account = (Account) request.getAttribute("account");
        service.setPosition(dto, builderId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{builderId}/get")
    public Builder get(@PathVariable String builderId) {
        Account account = (Account) request.getAttribute("account");
        return service.getById(builderId, account);
    }

}
