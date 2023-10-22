package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.BuilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DataPersistentBuilderService {

    @Autowired
    private BuilderRepository builderRepository;

    public Builder getById(String builderId, Account account) {
        Builder builder = builderRepository.findById(builderId).orElse(null);
        if (builder == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(builderId, account);
        return builder;
    }

    private void authorizeAccount(String builderId, Account account) {
        Builder builder = builderRepository.findById(builderId).orElse(null);
        if (builder == null || !builder.getComponentVersion().getComponent().getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }


    public Builder findFirstByComponentVersionOrderByPositionDesc(String id) {
        return builderRepository.findFirstByComponentVersionOrderByPositionDesc(id);
    }

    public void save(Builder builder) {
        builderRepository.save(builder);
    }

    public void delete(Builder builder) {
        builderRepository.delete(builder);
    }

    public List<Builder> findAllByComponentVersionIdOrderByPositionAsc(String id) {
        return builderRepository.findAllByComponentVersionIdOrderByPositionAsc(id);
    }

    public void saveAll(List<Builder> toUpdate) {
        builderRepository.saveAll(toUpdate);
    }
}
