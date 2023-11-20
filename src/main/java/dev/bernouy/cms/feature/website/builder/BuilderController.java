package dev.bernouy.cms.feature.website.builder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.builder.dto.req.ReqCreateBuilderDTO;
import dev.bernouy.cms.feature.website.builder.dto.req.ReqPositionBuilderDTO;
import dev.bernouy.cms.feature.website.builder.dto.res.ResBuilderDTO;
import dev.bernouy.cms.feature.website.builder.service.BusinessBuilderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/builder")
public class BuilderController {

    private BusinessBuilderService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public BuilderController(BusinessBuilderService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/")
    public ResponseEntity<String> createBuilder(@RequestBody ReqCreateBuilderDTO dto) {
        Account account = (Account) request.getAttribute("account");
        Builder builder = service.create(dto, account);
        return new ResponseEntity<>(builder.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{builderId}")
    public ResponseEntity<String> deleteBuilder(@PathVariable String builderId) {
        Account account = (Account) request.getAttribute("account");
        service.delete(builderId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{builderId}/position")
    public ResponseEntity<String> deleteBuilder(@RequestBody ReqPositionBuilderDTO dto, @PathVariable String builderId) {
        Account account = (Account) request.getAttribute("account");
        service.patchPosition(dto, builderId, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ResBuilderDTO> list(@RequestParam(required = false) String layoutId, @RequestParam(required = false) String pageId) {
        Account account = (Account) request.getAttribute("account");
        return service.listAllBuilder(layoutId, pageId, account);
    }

}
