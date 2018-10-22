package com.socketLabs.models;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a message attachment in the form of a byte array.
 */
public class Attachment {

    private String name;

    private String mimeType;

    private String contentId;

    private byte[] content;

    private List<CustomHeader> customHeaders;

    /**
     * Initializes a new instance of the Attachment class
     */
    public Attachment() {
    }


    /**
     * Initializes a new instance of the Attachment class
     * @param filePath The path to your attachment on your local system.
     * @throws IOException
     */
    public Attachment(String filePath) throws IOException {
        this.content = getContentFromFilePath(filePath);
        String[] splitPath = filePath.split("\\.");
        this.mimeType = getMimeTypeFromExtension(splitPath[splitPath.length - 1]);
    }

    /**
     * Initializes a new instance of the Attachment class
     * @param name The name for your attachment.
     * @param mimeType The MIME type for your attachment.
     * @param filePath  The path to your attachment on your local system.
     * @throws IOException
     */
    public Attachment(String name, String mimeType, String filePath) throws IOException {
        this.name = name;
        this.mimeType = mimeType;
        this.content = getContentFromFilePath(filePath);
    }

    /**
     * Initializes a new instance of the Attachment class
     * @param name The name for your attachment.
     * @param mimeType The MIME type for your attachment.
     * @param content A byte array containing the attachment content.
     */
    public Attachment(String name, String mimeType, byte[] content) {
        this.name = name;
        this.mimeType = mimeType;
        this.content = content;
    }

    //TODO - Constructor with name, mimeType, and Stream

    public byte[] getContent() {
        return this.content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentId() {
        return this.contentId;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getMimeType() {
        return this.mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders;
    }
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }

    private byte[] getContentFromFilePath(String filepath) throws IOException {
        return Files.readAllBytes(new File(filepath).toPath());
    }

    private String getMimeTypeFromExtension(String extension) {
        switch (extension.toLowerCase()) {
            case "txt":
            case "ini":
            case "sln":
            case "cs":
            case "js":
            case "config":
            case "vb":
                return "text/plain";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "bmp":
                return "image/bmp";
            case "csv":
                return "text/csv";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "gif":
                return "image/gif";
            case "html":
                return "text/html";
            case "pdf":
                return "application/pdf";
            case "png":
                return "image/png";
            case "ppt":
                return "application/vnd.ms-powerpoint";
            case "pptx":
                return "";
            case "xls":
                return "";
            case "xlsx":
                return "";
            case "xml":
                return "application/xml";
            case "zip":
                return "";
            case "wav":
                return "audio/wav";
            case "eml":
                return "message/rfc822";
            case "mp3":
                return "audio/mpeg";
            case "mp4":
                return "video/mp4";
            case "mov":
                return "video/quicktime";
            default:
                return "application/octet-stream";
        }

    }
}
