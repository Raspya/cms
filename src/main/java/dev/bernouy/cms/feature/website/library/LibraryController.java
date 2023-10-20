package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.library.dto.ReqAddRemoveVersionLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqNameLibrary;
import dev.bernouy.cms.feature.website.library.response.LibraryDTO;
import dev.bernouy.cms.feature.website.library.service.BusinessLogicLibraryService;
import dev.bernouy.cms.feature.website.version.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private BusinessLogicLibraryService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public LibraryController(BusinessLogicLibraryService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLibrary(@RequestBody ReqCreateLibrary dto) {
        Account account = (Account) request.getAttribute("account");
        Library library = service.create(dto, account);
        return new ResponseEntity<>(library.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<LibraryDTO> listLibrary(@RequestParam String projectId){
        Account account = (Account) request.getAttribute("account");
        return service.listLibrary(account, projectId);
    }

    @PostMapping("/{libraryId}/add")
    public ResponseEntity<String> add(@PathVariable String libraryId, @RequestBody ReqAddRemoveVersionLibrary dto) {
        Account account = (Account) request.getAttribute("account");
        service.add(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{libraryId}/remove")
    public ResponseEntity<String> remove(@PathVariable String libraryId, @RequestBody ReqAddRemoveVersionLibrary dto) {
        Account account = (Account) request.getAttribute("account");
        service.remove(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/{libraryId}/setName")
    public ResponseEntity<String> setName(@PathVariable String libraryId, @RequestBody ReqNameLibrary dto) {
        Account account = (Account) request.getAttribute("account");
        service.setName(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/{libraryId}/list")
    public ArrayList<Version> list(@PathVariable String libraryId) {
        Account account = (Account) request.getAttribute("account");
        return service.getVersionList(libraryId, account);
    }

}
