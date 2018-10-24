package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class MessageJson {

    @JsonProperty("To")
    private List<AddressJson> to;

    @JsonProperty("CC")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressJson> cc;

    @JsonProperty("BCC")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressJson> bcc;

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
    private AddressJson from;

    @JsonProperty("ReplyTo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressJson replyTo;

    @JsonProperty("Attachments")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AttachmentJson> attachments;

    @JsonProperty("Charset")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String charset;

    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomHeaderJson> customHeaders;

    @JsonProperty("MergeDataJson")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MergeDataJson mergeDataJson;

    public List<AddressJson> getTo() { return to; }
    public void setTo(List<AddressJson> value) { this.to = value; }

    public List<AddressJson> getCc() { return cc; }
    public void setCc(List<AddressJson> value) { this.cc = value; }

    public List<AddressJson> getBcc() { return bcc; }
    public void setBcc(List<AddressJson> value) { this.bcc = value; }

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

    public AddressJson getFrom() { return from; }
    public void setFrom(AddressJson value) { this.from = value; }

    public AddressJson getReplyTo() { return replyTo; }
    public void setReplyTo(AddressJson value) { this.replyTo = value; }

    public List<AttachmentJson> getAttachments() { return attachments; }
    public void setAttachments(List<AttachmentJson> value) { this.attachments = value; }

    public String getCharset() { return charset; }
    public void setCharset(String value) { this.charset = value; }

    public List<CustomHeaderJson> getCustomHeaders() { return customHeaders; }
    public void setCustomHeaders(List<CustomHeaderJson> value) { this.customHeaders = value; }

    public MergeDataJson getMergeDataJson() { return mergeDataJson; }
    public void setMergeDataJson(MergeDataJson value) { this.mergeDataJson = value; }
}
