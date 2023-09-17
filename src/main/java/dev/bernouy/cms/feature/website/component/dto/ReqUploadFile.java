package dev.bernouy.cms.feature.website.component.dto;

public class ReqUploadFile {

    private String file;

    public ReqUploadFile() {
    }

    public ReqUploadFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
