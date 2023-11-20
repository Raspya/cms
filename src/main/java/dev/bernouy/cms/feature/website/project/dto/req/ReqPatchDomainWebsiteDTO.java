package dev.bernouy.cms.feature.website.project.dto.req;

public class ReqPatchDomainWebsiteDTO {

    private String websiteId;
    private String domain;

    public ReqPatchDomainWebsiteDTO(String websiteId, String domain ){
        this.websiteId = websiteId;
        this.domain = domain;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
