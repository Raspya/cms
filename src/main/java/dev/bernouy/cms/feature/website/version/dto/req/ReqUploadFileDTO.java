package dev.bernouy.cms.feature.website.version.dto.req;

public class ReqUploadFileDTO {

    private String file;

    public ReqUploadFileDTO() {
    }

    public ReqUploadFileDTO(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
