package com.socketLabs.injectionApi.models;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Represents a message attachment in the form of a byte array.
 */
public class Attachment {

    /**
     * Name of attachment (displayed in email clients)
     */
    private String name;

    /**
     * The MIME type of the attachment.
     * Ex: text/plain, image/jpeg
     */
    private String mimeType;

    /**
     * When set, used to embed an image within the body of an email message.
     */
    private String contentId;

    /**
     * The BASE64 encoded string containing the contents of an attachment.
     */
    private byte[] content;

    /**
     * A list of custom headers added to the attachment.
     */
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

    /**
     * Gets the BASE64 encoded content of the Attachment.
     * @return byte[]
     */
    public byte[] getContent() {
        return this.content;
    }

    /**
     * Sets the BASE64 encoded content of the Attachment
     * @param content byte[]
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * Gets the ContentId of the Attachment.
     * @return String
     */
    public String getContentId() {
        return this.contentId;
    }

    /**
     * Sets the ContentId of the Attachment.
     * @param contentId String
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * Gets the MimeType of the Attachment.
     * @return String
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Sets the MimeType of the Attachment.
     * @param mimeType String
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Gets the name of the Attachment.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the Attachment.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of custom headers for the Attachment.
     * @return List<CustomHeader>
     */
    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders;
    }

    /**
     * Sets the list of custom headers for the Attachment.
     * @param customHeaders List<CustomHeader>
     */
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }

    /**
     * Takes a system filepath and returns the BASE64 encoded byte[].
     * @param filepath String
     * @return byte[]
     * @throws IOException
     */
    private byte[] getContentFromFilePath(String filepath) throws IOException {
        return Files.readAllBytes(new File(filepath).toPath());
    }

    /**
     * Takes a file extension, minus the '.', and returns the corresponding MimeType for the given extension.
     * @param extension String
     * @return String The corresponding MimeType for the given extension.
     */
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

    /**
     * Represents the attachment by name and mime type, useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.mimeType);
    }
}
