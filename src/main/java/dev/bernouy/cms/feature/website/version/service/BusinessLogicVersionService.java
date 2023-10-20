package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.FileSystem;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramModel.ParamModelRepository;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelFloat;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelInt;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelString;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.VersionRepository;
import dev.bernouy.cms.feature.website.version.formatting.request.ReqCreateVersion;
import dev.bernouy.cms.feature.website.version.formatting.request.ReqUploadFile;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.ComponentService;
import dev.bernouy.cms.feature.website.version.formatting.response.FormattingVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class BusinessLogicVersionService {

    private VersionRepository versionRepository;
    private ParamModelRepository paramModelRepository;
    private RegexComponent regexComponent;
    private ComponentService componentService;
    private DataFormattingVersionService dataFormattingVersionService;
    private AuthWebsiteService authWebsiteService;

    @Autowired
    public BusinessLogicVersionService(VersionRepository versionRepository, ParamModelRepository paramModelRepository, RegexComponent regexComponent, ComponentService componentService, DataFormattingVersionService dataFormattingVersionService, AuthWebsiteService authWebsiteService){
        this.regexComponent = regexComponent;
        this.versionRepository = versionRepository;
        this.paramModelRepository = paramModelRepository;
        this.componentService = componentService;
        this.dataFormattingVersionService = dataFormattingVersionService;
        this.authWebsiteService = authWebsiteService;
    }


    public Version create(ReqCreateVersion dto, Account account){
        Component comp = componentService.getById(dto.getComponentID());
        authorizeAccount(comp, account);

        int majorVersion = 1;
        int minorVersion = 0;
        int patchVersion = 0;

        String typeVersion = dto.getTypeVersion().toLowerCase();

        if (!typeVersion.equals("major") && !typeVersion.equals("minor") && !typeVersion.equals("patch") && !typeVersion.equals(""))
            throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_TYPE);

        Version oldVersion = versionRepository.getFirstByComponent_IdOrderByMajorVersionDescMinorVersionDescPatchVersionDesc(dto.getComponentID());
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

        update(version, null);

        return version;
    }

    public void uploadJS(ReqUploadFile dto, Account account, String versionId){
        Version version = authWebsiteService.isAuthByVersionAndGetIt(versionId, account);

        if (version.isDeploy()) throw new BasicException(WebsiteExceptionMessages.VERSION_ALREADY_DEPLOY);
        String url = FileSystem.COMPONENT_PATH + File.separator + "js" + File.separator + "C" + version.getId() + ".js";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(dto.getFile());
            fw.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void uploadCSS(ReqUploadFile dto, Account account, String versionId){
        Version version = authWebsiteService.isAuthByVersionAndGetIt(versionId, account);

        if (version.isDeploy()) throw new BasicException(WebsiteExceptionMessages.VERSION_ALREADY_DEPLOY);
        String url = FileSystem.COMPONENT_PATH + File.separator + "css" + File.separator + "C" + version.getId() + ".css";
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(dto.getFile());
            fw.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deploy(Account account, String versionId){
        Version version = authWebsiteService.isAuthByVersionAndGetIt(versionId, account);

        String urlJS = FileSystem.COMPONENT_PATH + File.separator + "js" + File.separator + "C" + version.getId() + ".js";
        String urlCSS = FileSystem.COMPONENT_PATH + File.separator + "css" + File.separator + "C" + version.getId() + ".css";
        File fileJS = new File(urlJS);
        File fileCSS = new File(urlCSS);

        if (!fileJS.exists() || !fileCSS.exists()) throw new BasicException(WebsiteExceptionMessages.FILE_DOES_NOT_EXIST);

        version.setDeploy(true);

    }

    public Version getById(String versionId ){
        return versionRepository.findById( versionId ).orElseThrow();
    }

    public Version getByIdAccount(String versionId, Account account ){
        Version version = versionRepository.findById(versionId).orElse(null);
        if (version == null) throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_ID);
        authorizeAccount(version.getComponent(), account);
        return version;
    }

    public void authorizeAccount(Component comp, Account account){
        if (!comp.getProject().getOwner().equals(account)) throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

    public void update(Version version, String parentId) {
        List<ParamModel> lstParamModel = paramModelRepository.findAllByComponentVersionAndParentId(version.getId(), parentId);
        ParamModel paramModelTmp = null;

        for (ParamModel paramModel : lstParamModel) {
            paramModelTmp = create(paramModel, version, parentId);
            paramModelRepository.save(paramModelTmp);
            update(version, paramModelTmp.getId());
        }

    }
    public ParamModel create(ParamModel oldParamModel, Version newVersion, String parentId){
        ParamModel paramModel = switch (oldParamModel.getType()) {
            case "string" -> new ParamModelString(((ParamModelString) oldParamModel).getMin(), ((ParamModelString) oldParamModel).getMax());
            case "int"    -> new ParamModelInt(((ParamModelInt) oldParamModel).getMin(), ((ParamModelInt) oldParamModel).getMax());
            case "float"  -> new ParamModelFloat(((ParamModelFloat) oldParamModel).getMin(), ((ParamModelFloat) oldParamModel).getMax());
            default       -> throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_TYPE);
        };

        paramModel.setComponentVersion(newVersion);
        paramModel.setParent(paramModelRepository.findById(parentId).orElse(null));
        paramModel.setName(oldParamModel.getName());
        paramModel.setKey(oldParamModel.getKey());
        paramModel.setType(oldParamModel.getType());
        paramModel.setValue(oldParamModel.getValue());
        paramModel.setPosition(oldParamModel.getPosition());
        return paramModel;
    }

}
