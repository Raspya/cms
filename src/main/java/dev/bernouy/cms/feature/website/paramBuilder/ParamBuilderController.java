package dev.bernouy.cms.feature.website.paramBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paramBuilder")
public class ParamBuilderController {

    private ParamBuilderService service;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamBuilderController(ParamBuilderService service, HttpServletResponse response, HttpServletRequest request) {
        this.service = service;
        this.response = response;
        this.request = request;
    }
}
