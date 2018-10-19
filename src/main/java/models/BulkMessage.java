package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkMessage implements interfaces.BulkMessage {

    @JsonProperty("To")
    private List<BulkRecipient> to;

    @JsonProperty("MergeData")
    private MergeData mergeData;

    @JsonProperty("Subject")
    private String subject;

    @JsonProperty("TextBody")
    private String plainTextBody;

    @JsonProperty("HtmlBody")
    private String htmlBody;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Attachment> attachments;

    @JsonProperty("Charset")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String charset;

    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomHeader> customHeaders;

    @Override
    public List<BulkRecipient> getTo() {
        return this.to;
    }

    @Override
    public void setTo(List<BulkRecipient> to) {
        this.to = to;
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
        return this.customHeaders;
    }

    @Override
    public void setCustomHeaders(List<CustomHeader> customheaders) {
        this.customHeaders = customheaders;
    }

    public BulkMessage() {
        this.mergeData = new MergeData();
    }

    @JsonProperty("MergeData")
    public MergeData getParsedMergeData() {
        MergeData mergeData = new MergeData();

        // set global merge data
        mergeData.setGlobal(this.mergeData.getGlobal());

        // set perMessage merge data
        for (BulkRecipient recipient : this.to) {
            if (recipient.getMergeData() != null) {
                this.mergeData.addToPerMessage(recipient.getMergeData());
            }
        }


        return mergeData;
    }

    @Override
    public void setGlobalMergeData(HashMap<String, String> globalMergeData) {
        this.mergeData.setGlobal(globalMergeData);
    }

    @JsonIgnore()
    @Override
    public HashMap<String, String> getGlobalMergeData() {
        return this.mergeData.getGlobal();
    }


}
