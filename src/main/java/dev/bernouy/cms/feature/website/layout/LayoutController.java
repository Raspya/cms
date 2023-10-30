package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.layout.dto.ReqCreateLayout;
import dev.bernouy.cms.feature.website.layout.dto.ReqSetDefaultLayout;
import dev.bernouy.cms.feature.website.layout.dto.ReqSetNameLayout;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/layout")
public class LayoutController {

    private LayoutService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public LayoutController(LayoutService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLayout(@RequestBody ReqCreateLayout dto) {
        Account account = (Account) request.getAttribute("account");
        Layout layout = service.create(dto, account);
        return new ResponseEntity<>(layout.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/{layoutId}/delete")
    public ResponseEntity<String> delete(@PathVariable String layoutId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(layoutId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{layoutId}/setName")
    public ResponseEntity<String> setName(@PathVariable String layoutId, @RequestBody ReqSetNameLayout dto) {
        Account account = (Account) request.getAttribute("account");
        service.setName(layoutId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{layoutId}/setDefault")
    public ResponseEntity<String> setDefault(@PathVariable String layoutId, @RequestBody ReqSetDefaultLayout dto) {
        Account account = (Account) request.getAttribute("account");
        service.setDefault(layoutId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{layoutId}/get")
    public Layout get(@PathVariable String layoutId) {
        Account account = (Account) request.getAttribute("account");
        return service.getById(layoutId, account);
    }
}
