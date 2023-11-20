package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.dto.res.ResVersionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormattingVersionService {

    public String formatVersion(int majorVersion, int minorVersion, int patchVersion){
        return majorVersion + "." + minorVersion + "." + patchVersion;
    }

    public List<ResVersionDTO> formatVersions(List<Version> versions){
        return versions.stream().map(version ->
                new ResVersionDTO(
                        version.getId(),
                        version.getComponent().getName(),
                        formatVersion(
                                version.getMajorVersion(),
                                version.getMinorVersion(),
                                version.getPatchVersion())))
                .collect(Collectors.toList());
    }

}
