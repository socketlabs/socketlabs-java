package com.socketLabs.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class Message {

    @JsonProperty("To")
    private List<Address> to;

    @JsonProperty("CC")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Address> cc;

    @JsonProperty("BCC")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Address> bcc;

    @JsonProperty("Subject")
    private String subject;

    @JsonProperty("TextBody")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String plainTextBody;

    @JsonProperty("HtmlBody")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String htmlBody;

    @JsonProperty("ApiTemplate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiTemplate;

    @JsonProperty("MailingId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mailingId;

    @JsonProperty("MessageId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

    @JsonProperty("From")
    private Address from;

    @JsonProperty("ReplyTo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Address replyTo;

    @JsonProperty("Attachments")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Attachment> attachments;

    @JsonProperty("Charset")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String charset;

    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomHeader> customHeaders;

    @JsonProperty("MergeData")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MergeData mergeData;

    public List<Address> getTo() { return to; }
    public void setTo(List<Address> value) { this.to = value; }

    public List<Address> getCc() { return cc; }
    public void setCc(List<Address> value) { this.cc = value; }

    public List<Address> getBcc() { return bcc; }
    public void setBcc(List<Address> value) { this.bcc = value; }

    public String getSubject() { return subject; }
    public void setSubject(String value) { this.subject = value; }

    public String getPlainTextBody() { return plainTextBody; }
    public void setPlainTextBody(String value) { this.plainTextBody = value; }

    public String getHtmlBody() { return htmlBody; }
    public void setHtmlBody(String value) { this.htmlBody = value; }

    public String getApiTemplate() { return apiTemplate; }
    public void setApiTemplate(String value) { this.apiTemplate = value; }

    public String getMailingId() { return mailingId; }
    public void setMailingId(String value) { this.mailingId = value; }

    public String getMessageId() { return messageId; }
    public void setMessageId(String value) { this.messageId = value; }

    public Address getFrom() { return from; }
    public void setFrom(Address value) { this.from = value; }

    public Address getReplyTo() { return replyTo; }
    public void setReplyTo(Address value) { this.replyTo = value; }

    public List<Attachment> getAttachments() { return attachments; }
    public void setAttachments(List<Attachment> value) { this.attachments = value; }

    public String getCharset() { return charset; }
    public void setCharset(String value) { this.charset = value; }

    public List<CustomHeader> getCustomHeaders() { return customHeaders; }
    public void setCustomHeaders(List<CustomHeader> value) { this.customHeaders = value; }

    public MergeData getMergeData() { return mergeData; }
    public void setMergeData(MergeData value) { this.mergeData = value; }
}
