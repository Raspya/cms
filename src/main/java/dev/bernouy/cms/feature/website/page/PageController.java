package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.feature.website.library.LibraryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {

    private PageService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public PageController(PageService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }
    
}
