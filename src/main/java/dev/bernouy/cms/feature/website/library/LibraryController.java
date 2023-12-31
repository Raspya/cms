package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.library.dto.req.ReqAddRemoveVersionLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.req.ReqCreateLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.req.ReqNameLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.response.ResLibraryDTO;
import dev.bernouy.cms.feature.website.library.service.BusinessLibraryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private BusinessLibraryService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public LibraryController(BusinessLibraryService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }

    @PostMapping("")
    public ResponseEntity<String> createLibrary(@RequestBody ReqCreateLibraryDTO dto) {
        Account account = (Account) request.getAttribute("account");
        Library library = service.create(dto, account);
        return new ResponseEntity<>(library.getId(), HttpStatus.CREATED);
    }

    /*@GetMapping("/listProjectComponents")
    public List<LibraryFormatting> listLibrary(@RequestParam String projectId){
        Account account = (Account) request.getAttribute("account");
        return service.listLibrary(account, projectId);
    }*/

    @PatchMapping("/{libraryId}/addVersion")
    public ResponseEntity<String> add(@PathVariable String libraryId, @RequestBody ReqAddRemoveVersionLibraryDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.add(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{libraryId}/removeVersion")
    public ResponseEntity<String> remove(@PathVariable String libraryId, @RequestBody ReqAddRemoveVersionLibraryDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.remove(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping("/{libraryId}/name")
    public ResponseEntity<String> setName(@PathVariable String libraryId, @RequestBody ReqNameLibraryDTO dto) {
        Account account = (Account) request.getAttribute("account");
        service.patchName(libraryId, dto, account);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ResLibraryDTO> list(@RequestParam String websiteId) {
        Account account = (Account) request.getAttribute("account");
        return service.getLibraryList(websiteId, account);
    }

}
