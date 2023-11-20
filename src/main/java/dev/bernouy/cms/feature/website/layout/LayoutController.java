package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqCreateLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqPatchDefaultLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqPatchNameLayoutDTO;
import dev.bernouy.cms.feature.website.layout.dto.res.ResLayoutDTO;
import dev.bernouy.cms.feature.website.layout.service.BusinessLayoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/layout")
public class LayoutController {

    private BusinessLayoutService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public LayoutController(BusinessLayoutService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> createLayout(@RequestBody ReqCreateLayoutDTO dto) {
        Account account = (Account) request.getAttribute("account");
        Layout layout = service.create(dto, account);
        return new ResponseEntity<>(layout.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{layoutId}")
    public ResponseEntity<String> delete(@PathVariable String layoutId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(layoutId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{layoutId}/name")
    public ResponseEntity<String> setName(@PathVariable String layoutId, @RequestBody ReqPatchNameLayoutDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchName(layoutId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{layoutId}/default")
    public ResponseEntity<String> setDefault(@PathVariable String layoutId, @RequestBody ReqPatchDefaultLayoutDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchDefault(layoutId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{layoutId}")
    public ResLayoutDTO get(@PathVariable String layoutId) {
        Account account = (Account) request.getAttribute("account");
        return service.getById(layoutId, account);
    }

    @GetMapping("/list")
    public List<ResLayoutDTO> list(@RequestParam String websiteId) {
        Account account = (Account) request.getAttribute("account");
        return service.list(websiteId, account );
    }
}
