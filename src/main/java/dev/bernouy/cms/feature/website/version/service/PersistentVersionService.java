package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentVersionService {

    @Autowired
    private VersionRepository versionRepository;

    public Version getLastVersion(String componentId){
        try{
            return versionRepository.getFirstByComponent_IdOrderByMajorVersionDescMinorVersionDescPatchVersionDesc(componentId);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public List<Version> getVersionsByComponentId(String componentId){
        try{
            return versionRepository.findAllByComponent_Id(componentId);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public Version getById(String id){
        Version v = versionRepository.findById(id).orElse(null);
        if ( v == null ) throw new MyDatabaseException();
        return v;
    }

    public void save(Version version){
        try{ versionRepository.save(version); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void delete(Version version){
        try{ versionRepository.delete(version); } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }



}
