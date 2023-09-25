package dev.bernouy.cms.feature.website.component.controller;

import dev.bernouy.cms.feature.website.component.dto.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.component.model.ParamModel;
import dev.bernouy.cms.feature.website.component.repository.ParamModelRepository;
import dev.bernouy.cms.feature.website.component.service.ParamModelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/component/version/param")
public class ParamModelController {

    private ParamModelService paramModelService;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    public ParamModelController(ParamModelService paramModelService, HttpServletRequest request, HttpServletResponse response){
        this.paramModelService = paramModelService;
        this.request = request;
        this.response = response;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createParamModel(@RequestBody ReqCreateParamModel dto) {

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
