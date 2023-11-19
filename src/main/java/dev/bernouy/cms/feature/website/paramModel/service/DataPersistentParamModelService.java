package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramModel.ParamModelRepository;
import dev.bernouy.cms.feature.website.paramModel.formatting.request.*;
import dev.bernouy.cms.feature.website.paramModel.formatting.response.ParamModelFormatting;
import dev.bernouy.cms.feature.website.paramModel.model.*;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.BusinessLogicVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataPersistentParamModelService {

    @Autowired
    private ParamModelRepository repository;


    public List<ParamModel> findAllByParentId(String id) {
        return repository.findAllByParentId(id);
    }
}
