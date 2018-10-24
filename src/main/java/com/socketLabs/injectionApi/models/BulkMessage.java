package com.socketLabs.injectionApi.models;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkMessage implements MessageBase {

    private List<BulkRecipient> to = new ArrayList<>();
    private Map<String, String> globalMergeData = new HashMap<String, String>();
    private String subject;
    private String plainTextBody;
    private String htmlBody;
    private @Nullable Integer apiTemplate;
    private String mailingId;
    private String messageId;
    private EmailAddress from;
    private EmailAddress replyTo;
    private List<Attachment> attachments = new ArrayList<>();
    private String charset;
    private List<CustomHeader> customHeaders = new ArrayList<>();

    public BulkMessage() {

    }

    public List<BulkRecipient> getTo() { return this.to; }
    public void setTo(List<BulkRecipient> value) {
        this.to = value;
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress {String}
     * @param name {String}
     */
    public void addToEmailAddress(String emailAddress, String name) {
        this.to.add(new BulkRecipient(emailAddress, name));
    }


    @Override
    public String getSubject() {
        return this.subject;
    }
    @Override
    public void setSubject(String value) {
        this.subject = value;
    }

    @Override
    public String getPlainTextBody() {
        return this.plainTextBody;
    }
    @Override
    public void setPlainTextBody(String value) {
        this.plainTextBody = value;
    }

    @Override
    public String getHtmlBody() {
        return this.htmlBody;
    }
    @Override
    public void setHtmlBody(String value) {
        this.htmlBody = value;
    }

    @Override
    public @Nullable Integer getApiTemplate() { return (this.apiTemplate == null) ? null : this.apiTemplate; }
    @Override
    public void setApiTemplate(@Nullable Integer value) {
        this.apiTemplate = value;
    }

    @Override
    public String getMailingId() {
        return this.mailingId;
    }
    @Override
    public void setMailingId(String value) {
        this.mailingId = value;
    }

    @Override
    public String getMessageId() {
        return this.messageId;
    }
    @Override
    public void setMessageId(String value) {
        this.messageId = value;
    }

    @Override
    public EmailAddress getFrom() {
        return this.from;
    }
    @Override
    public void setFrom(EmailAddress value) { this.from = value; }

    @Override
    public EmailAddress getReplyTo() {
        return this.replyTo;
    }
    @Override
    public void setReplyTo(EmailAddress value) { this.replyTo = value; }

    @Override
    public List<Attachment> getAttachments() {
        return this.attachments;
    }
    @Override
    public void setAttachments(List<Attachment> value) { this.attachments = value; }
    /**
     * Add an EmailAddress to the array of Attachment items
     * @param fileName
     * @throws IOException
     */
    public void addAttachments(String fileName) throws IOException {
        this.attachments.add(new Attachment(fileName));
    }

    @Override
    public String getCharset() {
        return this.charset;
    }
    @Override
    public void setCharset(String value) {
        this.charset = value;
    }

    @Override
    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders;
    }
    @Override
    public void setCustomHeaders(List<CustomHeader> value) {
        this.customHeaders = value;
    }
    /**
     * Add a CustomHeader to the message
     * @param name {String}
     * @param value {String}
     */
    public void addCustomHeader(String name, String value) {
        this.customHeaders.add(new CustomHeader(name, value ));
    }

    public Map<String, String> getMergeData() { return globalMergeData; }
    public void setMergeData(Map<String, String> value) { this.globalMergeData = value; }
    /**
     * Add a Global Merge Data to the message
     * @param field {String}
     * @param value {String}
     */
    public void addGlobalMergeData(String field, String value) {
        this.globalMergeData.put(field, value);
    }

    @Override
    public String toString() {
        return String.format("Recipients: %d, Subject, %s", this.to.size(), this.subject);
    }
}
