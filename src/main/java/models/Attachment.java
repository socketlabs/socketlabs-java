package models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachment implements interfaces.Attachment {

    @JsonProperty("Name")
    private String name;

    private String mimeType;

    @JsonProperty("ContentId")
    private String contentId;

    @JsonProperty("Content")
    private byte[] content;

    @JsonProperty("CustomHeaders")
    private List<CustomHeader> customHeaders;

    public Attachment() {
    }

    public Attachment(String filePath) throws IOException {
        this.content = getContentFromFilePath(filePath);
    }

    public Attachment(String name, String mimeType, String filePath) throws IOException {
        this.name = name;
        this.mimeType = mimeType;
        this.content = getContentFromFilePath(filePath);
    }

    public Attachment(String name, String mimeType, byte[] content) {
        this.name = name;
        this.mimeType = mimeType;
        this.content = content;
    }

    //TODO - Constructor with name, mimeType, and Stream

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String getContentId() {
        return this.contentId;
    }

    @Override
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public String getMimeType() {
        return this.mimeType;
    }

    @Override
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders;
    }

    @Override
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }

    private byte[] getContentFromFilePath(String filepath) throws IOException {
        return Files.readAllBytes(new File(filepath).toPath());
    }
}
