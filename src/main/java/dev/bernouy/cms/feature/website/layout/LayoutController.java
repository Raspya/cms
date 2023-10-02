package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.feature.account.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
