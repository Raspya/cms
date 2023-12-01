package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.config.properties.FileSystemProperties;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.service.PersistentComponentService;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.dto.req.ReqCreateVersionDTO;
import dev.bernouy.cms.feature.website.version.dto.req.ReqUploadFileDTO;
import dev.bernouy.cms.feature.website.version.dto.res.ResVersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class BusinessVersionService {

    private FormattingVersionService dataFormattingVersionService;
    private DeploymentVersionService deploymentVersionService;
    private PersistentComponentService persistentComponentService;
    private AuthProjectService authProjectService;
    private PersistentVersionService persistentVersionService;
    private FileSystemProperties fileSystemProperties;
    private CopyVersionService copyVersionService;

    @Autowired
    public BusinessVersionService(CopyVersionService copyVersionService, FormattingVersionService dataFormattingVersionService, DeploymentVersionService deploymentVersionService, PersistentComponentService persistentComponentService, AuthProjectService authProjectService, PersistentVersionService persistentVersionService, FileSystemProperties fileSystemProperties) {
        this.dataFormattingVersionService = dataFormattingVersionService;
        this.deploymentVersionService = deploymentVersionService;
        this.persistentComponentService = persistentComponentService;
        this.authProjectService = authProjectService;
        this.persistentVersionService = persistentVersionService;
        this.fileSystemProperties = fileSystemProperties;
        this.copyVersionService = copyVersionService;
    }

    public Version create(ReqCreateVersionDTO dto, Account account){
        Component comp = persistentComponentService.getById(dto.getComponentId());
        authProjectService.checkIsOwner(comp.getProject(), account);

        int majorVersion = 1;
        int minorVersion = 0;
        int patchVersion = 0;

        String typeVersion = dto.getTypeVersion().toLowerCase();

        if (!typeVersion.equals("major") && !typeVersion.equals("minor") && !typeVersion.equals("patch") && !typeVersion.equals(""))
            throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_TYPE);

        Version oldVersion = persistentVersionService.getLastVersion(dto.getComponentId());
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
        persistentVersionService.save(version);

        if (oldVersion != null)
            copyVersionService.duplicateSettings(version, null);
        return version;
    }

    public void uploadJS(ReqUploadFileDTO dto, Account account, String versionId){
        Version version = persistentVersionService.getById(versionId);
        authProjectService.checkIsOwner(version.getComponent().getProject(), account);
        if (version.isDeploy()) throw new BasicException(WebsiteExceptionMessages.VERSION_ALREADY_DEPLOY);
        deploymentVersionService.uploadJS(dto.getFile(), version.getId());
    }

    public void uploadCSS(ReqUploadFileDTO dto, Account account, String versionId){
        Version version = persistentVersionService.getById(versionId);
        authProjectService.checkIsOwner(version.getComponent().getProject(), account);
        if (version.isDeploy()) throw new BasicException(WebsiteExceptionMessages.VERSION_ALREADY_DEPLOY);
        deploymentVersionService.uploadCSS(dto.getFile(), version.getId());
    }

    public void deploy(Account account, String versionId){
        Version version = persistentVersionService.getById(versionId);
        authProjectService.checkIsOwner(version.getComponent().getProject(), account);
        String urlJS = fileSystemProperties.getFileSystem("root") + "/component" + File.separator + "js" + File.separator + "C" + version.getId() + ".js";
        String urlCSS = fileSystemProperties.getFileSystem("root") + "/component" + File.separator + "css" + File.separator + "C" + version.getId() + ".css";
        File fileJS = new File(urlJS);
        File fileCSS = new File(urlCSS);
        if (!fileJS.exists() || !fileCSS.exists()) throw new BasicException(WebsiteExceptionMessages.FILE_DOES_NOT_EXIST);
        version.setDeploy(true);
    }

    public List<ResVersionDTO> getListVersion(Account account, String componentId) {
        Component component = persistentComponentService.getById(componentId);
        authProjectService.checkIsOwner(component.getProject(), account);
        return dataFormattingVersionService.formatVersions(persistentVersionService.getVersionsByComponentId(componentId));
    }
}
