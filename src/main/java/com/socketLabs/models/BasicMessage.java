package com.socketLabs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BasicMessage implements MessageBase {

    @JsonProperty("To")
    private List<EmailAddress> to;

    @JsonProperty("CC")
    private List<EmailAddress> cc;

    @JsonProperty("BCC")
    private List<EmailAddress> bcc;

    @JsonProperty("Subject")
    private String subject;

    @JsonProperty("TextBody")
    private String plainTextBody;

    @JsonProperty("HtmlBody")
    private String htmlBody;

    @JsonProperty("ApiTemplate")
    private String apiTemplate;

    @JsonProperty("MailingId")
    private String mailingId;

    @JsonProperty("MessageId")
    private String messageId;

    @JsonProperty("From")
    private EmailAddress from;

    @JsonProperty("ReplyTo")
    private EmailAddress replyTo;

    @JsonProperty("Attachments")
    private List<Attachment> attachments;

    @JsonProperty("Charset")
    private String charset;

    @JsonProperty("CustomHeaders")
    private List<CustomHeader> customHeaders;


    public BasicMessage() {
        this.to = new ArrayList<>();
    }

    public List<EmailAddress> getTo() {
        return this.to;
    }

    public void setTo(List<EmailAddress> to) {
        this.to = to;
    }

    public List<EmailAddress> getCc() {
        return this.cc;
    }

    public void setCc(List<EmailAddress> cc) {
        this.cc = cc;
    }

    public List<EmailAddress> getBcc() {
        return this.bcc;
    }

    public void setBcc(List<EmailAddress> bcc) {
        this.bcc = bcc;
    }

    
    public String getSubject() {
        return this.subject;
    }

    
    public void setSubject(String subject) {
        this.subject = subject;
    }

        public String getPlainTextBody() {
        return this.plainTextBody;
    }
    public void setPlainTextBody(String plainTextBody) {
        this.plainTextBody = plainTextBody;
    }

    public String getHtmlBody() {
        return this.htmlBody;
    }
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    // TODO: change to int
    public String getApiTemplate() {
        return this.apiTemplate;
    }
    public void setApiTemplate(String apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    public String getMailingId() {
        return this.mailingId;
    }
    public void setMailingId(String mailingId) {
        this.mailingId = mailingId;
    }

    public String getMessageId() {
        return this.messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public EmailAddress getFrom() {
        return this.from;
    }
    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    public EmailAddress getReplyTo() {
        return this.replyTo;
    }
    public void setReplyTo(EmailAddress replyTo) {
        this.replyTo = replyTo;
    }

    public List<Attachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getCharset() {
        return this.charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders ;
    }
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public List<EmailAddress> addToAddress(EmailAddress address) {
        this.to.add(address);
        return this.to;
    }
}
