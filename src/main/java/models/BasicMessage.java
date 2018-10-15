package models;

import java.util.List;

public class BasicMessage implements interfaces.BasicMessage {

    private List<EmailAddress> to;
    private List<EmailAddress> cc;
    private List<EmailAddress> bcc;
    private String subject;
    private String plainTextBody;
    private String htmlBody;
    private int apiTemplate;
    private String mailingId;
    private String messageId;
    private EmailAddress from;
    private EmailAddress replyTo;
    private List<Attachment> attachments;
    private String charset;
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
    public int getApiTemplate() {
        return this.apiTemplate;
    }

    @Override
    public void setApiTemplate(int apiTemplate) {
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
