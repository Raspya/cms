package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataPersistentVersionService {

    @Autowired
    private VersionRepository versionRepository;

    public Version getById(String id){
        Version v = versionRepository.findById(id).orElse(null);
        if ( v == null ) throw new BasicException("Version not found");
        return v;
    }

    public void save(Version version){
        try{ versionRepository.save(version); } catch ( Exception e ){
            throw new BasicException("Error during save version");
        }
    }

    public void delete(Version version){
        try{ versionRepository.delete(version); } catch ( Exception e ){
            throw new BasicException("Error during delete version");
        }
    }



}
