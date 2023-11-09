package dev.bernouy.cms.feature.website.page.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.service.DataPersistentBuilderService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.paramBuilder.service.DataPersistentParamBuilderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentPageService {

    private DataPersistentBuilderService dataPersistentBuilderService;
    private DataPersistentParamBuilderService dataPersistentParamBuilderService;

    public DeploymentPageService(DataPersistentBuilderService dataPersistentBuilderService, DataPersistentParamBuilderService dataPersistentParamBuilderService) {
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
    }

    public void writePageFile(Page page){

        String pageContent = "";
        List<Builder> builders = dataPersistentBuilderService.listAllBuilderByPageId(page.getId());
        pageContent += "export default function P" + page.getId() + "(){ return (";
        for ( Builder builder : builders ){
            pageContent += "<C" + builder.getComponentVersion().getId() + "/>";
        }
        pageContent += ");}";
    }

}
