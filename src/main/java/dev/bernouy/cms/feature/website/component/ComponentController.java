package dev.bernouy.cms.feature.website.component;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.formatting.request.ReqCreateComponentDTO;
import dev.bernouy.cms.feature.website.component.formatting.request.ReqDeleteComponentDTO;
import dev.bernouy.cms.feature.website.component.formatting.request.ReqEditNameComponentDTO;
import dev.bernouy.cms.feature.website.component.formatting.response.InternalComponentFormat;
import dev.bernouy.cms.feature.website.component.service.BusinessLogicComponentService;
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

    private BusinessLogicComponentService componentService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ComponentController(BusinessLogicComponentService componentService, HttpServletResponse response, HttpServletRequest request) {
        this.componentService = componentService;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody ReqCreateComponentDTO dto ) {
        Account account = (Account) request.getAttribute("account");
        Component component = componentService.create(
                dto.getName(),
                dto.getWebsiteId(),
                account );
        return new ResponseEntity<>(component.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/name")
    public ResponseEntity<String> editName(@RequestBody ReqEditNameComponentDTO dto ) {
        Account account = (Account) request.getAttribute("account");
        componentService.editName(
                dto.getName(),
                dto.getComponentId(),
                account );
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @DeleteMapping("/{componentId}")
    public ResponseEntity<String> delete(@PathVariable String componentId ) {
        Account account = (Account) request.getAttribute("account");
        componentService.delete(
                componentId,
                account );
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<InternalComponentFormat> list(@RequestParam String websiteId) {
        Account account = (Account) request.getAttribute("account");
        return componentService.list(websiteId, account );
    }
}
