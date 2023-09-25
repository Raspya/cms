package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.FileSystem;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountExceptionMessages;
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

        int majorVersion = 1;
        int minorVersion = 0;
        int patchVersion = 0;

        String typeVersion = dto.getTypeVersion().toLowerCase();

        if (!typeVersion.equals("major") && !typeVersion.equals("minor") && !typeVersion.equals("patch") && !typeVersion.equals(""))
            throw new BasicException(ComponentExceptionMessages.INVALID_VERSION_TYPE);

        Version oldVersion = versionRepository.getByComponentIdOrderByPatchVersion(dto.getComponentID());
        if (oldVersion != null) {
            majorVersion = oldVersion.getMajorVersion();
            minorVersion = oldVersion.getMinorVersion();
            patchVersion = oldVersion.getPatchVersion();

            switch (typeVersion) {
                case "major" -> {
                    majorVersion += 1;
                    minorVersion = 0;
                    patchVersion = 0;
                }
                case "minor" -> {
                    minorVersion += 1;
                    patchVersion = 0;
                }
                case "patch" -> patchVersion += 1;
            }
        }

        Version version = new Version();
        version.setMajorVersion(majorVersion);
        version.setMinorVersion(minorVersion);
        version.setPatchVersion(patchVersion);
        version.setComponent(comp);
        version.setDeploy(false);
        versionRepository.save(version);
        return version;
    }

    public void uploadJS(ReqUploadFile dto, Account account, String versionId){
        Version version = versionRepository.findById(versionId).orElseThrow();
        authorizeAccount(version.getComponent(), account);

        String url = FileSystem.COMPONENT_PATH + File.separator + "js" + File.separator + "C" + version.getId() + ".js";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(dto.getFile());
            fw.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void uploadCSS(ReqUploadFile dto, Account account, String versionId){
        Version version = versionRepository.findById(versionId).orElseThrow();
        authorizeAccount(version.getComponent(), account);

        String url = FileSystem.COMPONENT_PATH + File.separator + "css" + File.separator + "C" + version.getId() + ".css";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(dto.getFile());
            fw.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deploy(Account account, String versionId){
        Version version = versionRepository.findById(versionId).orElseThrow();
        authorizeAccount(version.getComponent(), account);

        String urlJS = FileSystem.COMPONENT_PATH + File.separator + "js" + File.separator + "C" + version.getId() + ".js";
        String urlCSS = FileSystem.COMPONENT_PATH + File.separator + "css" + File.separator + "C" + version.getId() + ".css";
        File fileJS = new File(urlJS);
        File fileCSS = new File(urlCSS);

        if (!fileJS.exists() || !fileCSS.exists()) throw new BasicException(ComponentExceptionMessages.FILE_DOES_NOT_EXIST);

        version.setDeploy(true);

        System.out.println("-----------");
        System.out.println("-----------");
        System.out.println(version.isDeploy());
        System.out.println("-----------");
        System.out.println("-----------");


    }

    private void authorizeAccount(Component comp, Account account){
        if (!comp.getProject().getOwner().equals(account)) throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }





}
