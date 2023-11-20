package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.paramModel.ParamModelRepository;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentParamModelService {

    private ParamModelRepository paramModelRepository;

    public PersistentParamModelService(ParamModelRepository paramModelRepository) {
        this.paramModelRepository = paramModelRepository;
    }

    public void delete(ParamModel paramModel) {
        try{
            paramModelRepository.deleteById(paramModel.getId());
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public void save(ParamModel paramModel) {
        try{
            paramModelRepository.save(paramModel);
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public void saveAll(List<ParamModel> paramModels) {
        try{
            paramModelRepository.saveAll(paramModels);
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public ParamModel getById(String id){
        try{
            ParamModel paramModel = paramModelRepository.findById(id).orElse(null);
            if (paramModel == null) throw new MyDatabaseException();
            return paramModel;
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public Integer getLastPosition(Version version){
        try{
            ParamModel paramModel = paramModelRepository.findFirstByComponentVersionIdOrderByPositionDesc(version.getId());
            if (paramModel == null) return 0;
            return paramModel.getPosition();
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public List<ParamModel> findAllByVersion(Version version){
        try{
            return paramModelRepository.findAllByComponentVersionIdOrderByPositionAsc(version.getId());
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public List<ParamModel> findAllByParentId(String id) {
        return paramModelRepository.findAllByParentId(id);
    }


    public List<ParamModel> findAllByVersionAndParent(Version version, ParamModel parent){
        try{
            return paramModelRepository.findAllByComponentVersionIdAndParentId(version.getId(), parent.getId());
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

    public ParamModel getByParent(ParamModel paramModel){
        try {
            return paramModelRepository.findByParentId(paramModel.getId());
        } catch (Exception e) {
            throw new MyDatabaseException();
        }
    }

}
