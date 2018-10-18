package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BasicMessage implements interfaces.BasicMessage {

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
    }

    @Override
    public List<EmailAddress> getTo() {
        return this.to;
    }

    @Override
    public void setTo(List<EmailAddress> to) {
        this.to = to;
    }

    @Override
    public List<EmailAddress> getCc() {
        return this.cc;
    }

    @Override
    public void setCc(List<EmailAddress> cc) {
        this.cc = cc;
    }

    @Override
    public List<EmailAddress> getBcc() {
        return this.bcc;
    }

    @Override
    public void setBcc(List<EmailAddress> bcc) {
        this.bcc = bcc;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getPlainTextBody() {
        return this.plainTextBody;
    }

    @Override
    public void setPlainTextBody(String plainTextBody) {
        this.plainTextBody = plainTextBody;
    }

    @Override
    public String getHtmlBody() {
        return this.htmlBody;
    }

    @Override
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    @Override
    public String getApiTemplate() {
        return this.apiTemplate;
    }

    @Override
    public void setApiTemplate(String apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public String getMailingId() {
        return this.mailingId;
    }

    @Override
    public void setMailingId(String mailingId) {
        this.mailingId = mailingId;
    }

    @Override
    public String getMessageId() {
        return this.messageId;
    }

    @Override
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public EmailAddress getFrom() {
        return this.from;
    }

    @Override
    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    @Override
    public EmailAddress getReplyTo() {
        return this.replyTo;
    }

    @Override
    public void setReplyTo(EmailAddress replyTo) {
        this.replyTo = replyTo;
    }

    @Override
    public List<Attachment> getAttachments() {
        return this.attachments;
    }

    @Override
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String getCharset() {
        return this.charset;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public List<CustomHeader> getCustomHeaders() {
        return this.customHeaders ;
    }

    @Override
    public void setCustomHeaders(List<CustomHeader> customHeaders) {
        this.customHeaders = customHeaders;
    }
}
