package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.service.PersistentParamBuilderService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeploymentBuilderService {

    private PersistentParamBuilderService dataPersistentParamBuilderService;

    public DeploymentBuilderService(PersistentParamBuilderService dataPersistentParamBuilderService) {
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
    }

    private String getStringCallParams(Builder builder){
        List<ParamBuilder> paramBuilders = dataPersistentParamBuilderService.listParamBuilderByBuilderId(builder.getId());
        StringBuilder str = new StringBuilder("{");
        for ( ParamBuilder paramBuilder : paramBuilders ){
            str.append(getStringCallParamsRec(paramBuilder));
            if ( !paramBuilder.equals(paramBuilders.get(paramBuilders.size()-1)) ) str.append(",");
        }

        str.append("}");
        return str.toString();
    }

    private String getStringCallParamsRec(ParamBuilder paramBuilder){
        List<ParamBuilder> paramBuilders = dataPersistentParamBuilderService.listAllParamBuilderById(paramBuilder.getId());
        StringBuilder str = new StringBuilder(paramBuilder.getParamModel().toParamString());
        if (paramBuilder.getParamModel().childAvailable()){
            for ( ParamBuilder paramBuilder1 : paramBuilders ){
                str.append(getStringCallParamsRec(paramBuilder1));
                if ( !paramBuilder1.equals(paramBuilders.get(paramBuilders.size()-1)) ) str.append(",");
            }
        }
        return str.toString();
    }

    public String getStringCallElement(List<Builder> builders){
        String str = "";
        for(Builder builder : builders){
            str += "<C" + builder.getComponentVersion().getId() + "data={"+getStringCallParams(builder)+"}" + "/>";
        }
        return str;
    }

    public String getStringImport(List<Builder> builders){
        Set<String> imports = new HashSet<>();
        for ( Builder builder : builders )
            imports.add(builder.getId());
        String str = "";
        for ( String s : imports ){
            str += "import C" + s +
                    " from \"share_component/component/C" + s +
                    "\";";
        }
        return str;
    }

}
