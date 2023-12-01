package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentParamBuilderService {

    @Autowired
    private ParamBuilderRepository paramBuilderRepository;

    public ParamBuilder getById(String paramBuilderId) {
        try{
            ParamBuilder paramBuilder = paramBuilderRepository.findById(paramBuilderId).orElse(null);
            if (paramBuilder == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
            return paramBuilder;
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void save(ParamBuilder paramBuilder) {
        try{
            paramBuilderRepository.save(paramBuilder);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<ParamBuilder> listAllParamBuilderByParamModelId(String paramModelId) {
        try{
            return paramBuilderRepository.findAllByParamModelId(paramModelId);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<ParamBuilder> listParamBuilderByBuilderId(String builderId) {
        try{
            return paramBuilderRepository.findByParamModelParentIsNullAndBuilderId(builderId);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<ParamBuilder> listAllParamBuilderById(String paramBuilderId) {
        try{
            return paramBuilderRepository.findAllById(paramBuilderId);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }
}
