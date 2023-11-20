package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.service.PersistentBuilderService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.paramBuilder.service.PersistentParamBuilderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentPageService {

    private PersistentBuilderService dataPersistentBuilderService;
    private PersistentParamBuilderService dataPersistentParamBuilderService;

    public DeploymentPageService(PersistentBuilderService dataPersistentBuilderService, PersistentParamBuilderService dataPersistentParamBuilderService) {
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
    }

    public void writePageFile(Page page){

        String pageContent = "";
        List<Builder> builders = dataPersistentBuilderService.getBuildersByPage(page.getId());
        pageContent += "export default function P" + page.getId() + "(){ return (";
        for ( Builder builder : builders ){
            pageContent += "<C" + builder.getComponentVersion().getId() + "/>";
        }
        pageContent += ");}";
    }

}
