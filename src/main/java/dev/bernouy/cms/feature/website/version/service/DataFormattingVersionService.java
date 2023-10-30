package dev.bernouy.cms.feature.website.version.service;

import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.formatting.response.FormattingVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFormattingVersionService {

    public String formatVersion(int majorVersion, int minorVersion, int patchVersion){
        return majorVersion + "." + minorVersion + "." + patchVersion;
    }

    public List<FormattingVersion> formatVersions(List<Version> versions){
        return versions.stream().map(version ->
                new FormattingVersion(
                        version.getId(),
                        version.getComponent().getName(),
                        formatVersion(
                                version.getMajorVersion(),
                                version.getMinorVersion(),
                                version.getPatchVersion())))
                .collect(Collectors.toList());
    }

}
