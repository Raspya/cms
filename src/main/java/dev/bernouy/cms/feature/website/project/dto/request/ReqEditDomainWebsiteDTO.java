package dev.bernouy.cms.feature.website.project.dto.request;

public class ReqEditDomainWebsiteDTO {

    private String websiteID;
    private String domain;

    public ReqEditDomainWebsiteDTO(String websiteID, String domain ){
        this.websiteID = websiteID;
        this.domain = domain;
    }

    public String getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(String websiteID) {
        this.websiteID = websiteID;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
