package dev.bernouy.cms.feature.website.component.controller;

import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/component/version")
public class VersionController {

    @PostMapping("/create")
    public ResponseEntity<String> createVersion(@RequestBody ReqCreateVersion dto){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/version/{versionID}/uploadJS")
    public ResponseEntity<String> uploadJS(@RequestBody ReqUploadFile dto, @PathVariable String versionID){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/version/{versionID}/uploadCSS")
    public ResponseEntity<String> uploadCSS(@RequestBody ReqUploadFile dto, @PathVariable String versionID){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/version/{versionID}/deploy")
    public ResponseEntity<String> deployVersion(@PathVariable String versionID){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

}
