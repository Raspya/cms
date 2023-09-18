package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.FileSystem;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.ComponentExceptionMessages;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import dev.bernouy.cms.feature.website.component.model.Component;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VersionService {

    private VersionRepository versionRepository;
    private RegexComponent regexComponent;
    private ComponentService componentService;

    @Autowired
    public VersionService(VersionRepository versionRepository, RegexComponent regexComponent, ComponentService componentService){
        this.regexComponent = regexComponent;
        this.versionRepository = versionRepository;
        this.componentService = componentService;
    }


    public Version create(ReqCreateVersion dto, Account account){
        Component comp = componentService.getById(dto.getComponentID());
        authorizeAccount(comp, account);
        if (dto.getMajorVersion() < 0) throw new BasicException(ComponentExceptionMessages.VERSION_INVALID);
        if (dto.getMinorVersion() < 0) throw new BasicException(ComponentExceptionMessages.VERSION_INVALID);
        if (dto.getPatchVersion() < 0) throw new BasicException(ComponentExceptionMessages.VERSION_INVALID);
        Version version = new Version();
        version.setMajorVersion(dto.getMajorVersion());
        version.setMinorVersion(dto.getMinorVersion());
        version.setPatchVersion(dto.getPatchVersion());
        version.setComponent(comp);
        version.setDeploy(false);
        versionRepository.save(version);
        return version;
    }

    public void uploadJS(ReqUploadFile dto, Account account, String versionId){
        Version version = versionRepository.findById(versionId).orElseThrow();
        authorizeAccount(version.getComponent(), account);
        String url = FileSystem.COMPONENT_PATH + File.separator + version.getComponent().getId() + File.separator + "C" + version.getId() + ".js";
        try {
            System.out.println(url);
            Path path = Paths.get(url);
            if (!Files.exists(path)){
                Files.createDirectories(path);
            }
            FileWriter fw = new FileWriter(url);
            fw.write(dto.getFile());
            fw.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void authorizeAccount(Component comp, Account account){
        if (!comp.getProject().getOwner().equals(account)) throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }



}
