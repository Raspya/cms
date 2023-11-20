package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.BuilderRepository;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentBuilderService {

    @Autowired
    private BuilderRepository builderRepository;

    public Builder getById(String builderId) {
        Builder builder = builderRepository.findById(builderId).orElse(null);
        if (builder == null) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_ID);
        return builder;
    }

    public void save(Builder builder) {
        try{
            builderRepository.save(builder);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void delete(Builder builder) {
        try{
            builderRepository.delete(builder);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void saveAll(List<Builder> builders) {
        try{
            builderRepository.saveAll(builders);
        } catch (Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Builder> getBuildersByLayout(String layout) {
        try{
            return builderRepository.findAllByLayoutIdOrderByPositionAsc(layout);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Builder> getBuildersByPage(String page) {
        try{
            return builderRepository.findAllByPageIdOrderByPositionAsc(page);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Builder> getBuilders(Builder builder){
        try{
            if ( builder.getPage() == null ){
                return builderRepository.findAllByLayoutIdOrderByPositionAsc(builder.getLayout().getId());
            } else {
                return builderRepository.findAllByPageIdOrderByPositionAsc(builder.getPage().getId());
            }
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public int getMaxPos(Builder builder) {
        try{
            if ( builder.getPage() == null ){
                return builderRepository.findFirstBuilderByLayoutIdOrderByPositionDesc(builder.getLayout().getId()).getPosition();
            } else {
                return builderRepository.findFirstBuilderByPageIdOrderByPositionDesc(builder.getPage().getId()).getPosition();
            }
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Builder getFirstByPageId(String id) {
        try{
            return builderRepository.findFirstBuilderByPageIdOrderByPositionDesc(id);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Builder getFirstByLayoutId(String id) {
        try{
            return builderRepository.findFirstBuilderByLayoutIdOrderByPositionDesc(id);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

}
