package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.feature.website.layout.LayoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private LibraryService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public LibraryController(LibraryService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }
}
