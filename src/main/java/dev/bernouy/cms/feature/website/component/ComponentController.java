package dev.bernouy.cms.feature.website.component;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.dto.*;
import dev.bernouy.cms.feature.website.component.ComponentService;
import dev.bernouy.cms.feature.website.component.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/component")
public class ComponentController {

    private ComponentService componentService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ComponentController(ComponentService componentService, HttpServletResponse response, HttpServletRequest request) {
        this.componentService = componentService;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ReqCreateComponentDTO dto ) {
        Account account = (Account) request.getAttribute("account");
        Component component = componentService.create(
                dto.getName(),
                dto.getWebsiteId(),
                account );
        return new ResponseEntity<>(component.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/editName")
    public ResponseEntity<String> editName(@RequestBody ReqEditNameComponentDTO dto ) {
        Account account = (Account) request.getAttribute("account");
        componentService.editName(
                dto.getName(),
                dto.getComponentId(),
                account );
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody ReqDeleteComponentDTO dto ) {
        Account account = (Account) request.getAttribute("account");
        componentService.delete(
                dto.getComponentId(),
                account );
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list/{websiteId}")
    public List<Component> list( @PathVariable String websiteId ) {
        Account account = (Account) request.getAttribute("account");
        return componentService.list(
                websiteId,
                account );
    }


}