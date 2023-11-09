package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataPersistentParamBuilderService {

    @Autowired
    private ParamBuilderRepository paramBuilderRepository;

    public ParamBuilder getById(String paramModelId , Account account) {
        ParamBuilder paramBuilder = paramBuilderRepository.findById(paramModelId).orElse(null);
        if (paramBuilder == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(paramModelId, account);
        return paramBuilder;
    }

    private void authorizeAccount(String builderId, Account account) {
        ParamBuilder paramBuilder = paramBuilderRepository.findById(builderId).orElse(null);
        if (paramBuilder == null || !paramBuilder.getBuilder().getComponentVersion().getComponent().getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

    public void save(ParamBuilder paramBuilder) {
        paramBuilderRepository.save(paramBuilder);
    }

    public List<ParamBuilder> listAllParamBuilderByParamModelId(String paramModelId) {
        return paramBuilderRepository.findAllByParamModelId(paramModelId);
    }

    public List<ParamBuilder> listParamBuilderByBuilderId(String builderId) {
        return paramBuilderRepository.findByParamModelParentIsNullAndBuilderId(builderId);
    }

    public List<ParamBuilder> listParamBuilderStepByParamModelIdAndBuilderId(String builderId, String paramModelId) {
        return paramBuilderRepository.findAllByBuilderIdAndParamModelParentId(builderId, paramModelId);
    }

    public List<ParamBuilder> listAllParamBuilderById(String paramBuilderId) {
        return paramBuilderRepository.findAllById(paramBuilderId);
    }
}
