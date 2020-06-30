package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a message for sending to the Injection Api.
 * To be serialized into JSON string before sending to the Injection Api.
 */
class MessageJson {


    /** The list of To recipients. */
    @JsonProperty("To")
    private List<AddressJson> to;

    /** The list of CC recipients. */
    @JsonProperty("CC")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AddressJson> cc;

    /** The list of BCC recipients. */
    @JsonProperty("BCC")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AddressJson> bcc;

    /** The message Subject. */
    @JsonProperty("Subject")
    private String subject;

    /** The plain text portion of the message body. */
    @JsonProperty("TextBody")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String plainTextBody;

    /** The HTML portion of the message body. */
    @JsonProperty("HtmlBody")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String htmlBody;

    /** The AMP portion of the message body. */
    @JsonProperty("AmpBody")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ampBody;

    /** The Api Template for the messaget. */
    @JsonProperty("ApiTemplate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiTemplate;

    /** The custom MailingId for the message. */
    @JsonProperty("MailingId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mailingId;

    /** The custom MessageId for the message. */
    @JsonProperty("MessageId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

    /** The From address. */
    @JsonProperty("From")
    private AddressJson from;

    /** The Reply To address. */
    @JsonProperty("ReplyTo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressJson replyTo;

    /** The list of attachments. */
    @JsonProperty("Attachments")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AttachmentJson> attachments;

    /** The optional character set for the message. */
    @JsonProperty("CharSet")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String charSet;

    /** The list of custom message headers for the message. */
    @JsonProperty("CustomHeaders")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CustomHeaderJson> customHeaders;

    /** The the list of merge data. */
    @JsonProperty("MergeData")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MergeDataJson mergeData;

    /**
     * Create a new instance of the MessageJson class
     */
    public MessageJson () {
        this.to = new ArrayList<>();
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
        this.attachments = new ArrayList<>();
    }

    /**
     * Get the list of To recipients.
     * @return List<AddressJson>
     */
    public List<AddressJson> getTo() { return to; }
    /**
     * Set the list of To recipients.
     * @param value List<AddressJson>
     */
    public void setTo(List<AddressJson> value) { this.to = value; }

    /**
     * Get the list of CC recipients.
     * @return List<AddressJson>
     */
    public List<AddressJson> getCc() { return cc; }
    /**
     * Set the list of CC recipients.
     * @param value List<AddressJson>
     */
    public void setCc(List<AddressJson> value) { this.cc = value; }

    /**
     * Get the list of BCC recipients.
     * @return List<AddressJson>
     */
    public List<AddressJson> getBcc() { return bcc; }
    /**
     * Set the list of BCC recipients.
     * @param value List<AddressJson>
     */
    public void setBcc(List<AddressJson> value) { this.bcc = value; }

    /**
     * Get the message Subject.
     * @return String
     */
    public String getSubject() { return subject; }
    /**
     * Set the message Subject.
     * @param value String
     */
    public void setSubject(String value) { this.subject = value; }

    /**
     * Get the plain text portion of the message body.
     * @return String
     */
    public String getPlainTextBody() { return plainTextBody; }
    /**
     * Set the plain text portion of the message body.
     * @param value String
     */
    public void setPlainTextBody(String value) { this.plainTextBody = value; }

    /**
     * Get the HTML portion of the message body.
     * @return String
     */
    public String getHtmlBody() { return htmlBody; }
    /**
     * Set the HTML portion of the message body.
     * @param value String
     */
    public void setHtmlBody(String value) { this.htmlBody = value; }

    /**
     * Gets the AMP portion of the message body.
     * @return String
     */
    public String getAmpBody() { return this.ampBody; }
    /**
     * Sets the AMP portion of the message body.
     * @param value String
     */
    public void setAmpBody(String value) { this.ampBody = value; }

    /**
     * Get the Api Template for the message.
     * @return String
     */
    public String getApiTemplate() { return apiTemplate; }
    /**
     * Set the Api Template for the message.
     * @param value String
     */
    public void setApiTemplate(String value) { this.apiTemplate = value; }

    /**
     * Get the custom MailingId for the message.
     * @return String
     */
    public String getMailingId() { return mailingId; }
    /**
     * Set the custom MailingId for the message.
     * @param value String
     */
    public void setMailingId(String value) { this.mailingId = value; }

    /**
     * Get the custom MessageId for the message.
     * @return String
     */
    public String getMessageId() { return messageId; }
    /**
     * Set the custom MessageId for the message.
     * @param value String
     */
    public void setMessageId(String value) { this.messageId = value; }

    /**
     * Get the From address.
     * @return AddressJson
     */
    public AddressJson getFrom() { return from; }
    /**
     * Set the From address.
     * @param value AddressJson
     */
    public void setFrom(AddressJson value) { this.from = value; }

    /**
     * Get the Reply To address.
     * @return AddressJson
     */
    public AddressJson getReplyTo() { return replyTo; }
    /**
     * Set the Reply To address.
     * @param value AddressJson
     */
    public void setReplyTo(AddressJson value) { this.replyTo = value; }

    /**
     * Get the list of attachments.
     * @return List<AttachmentJson>
     */
    public List<AttachmentJson> getAttachments() { return attachments; }
    /**
     * Set the list of attachments.
     * @param value List<AttachmentJson>
     */
    public void setAttachments(List<AttachmentJson> value) { this.attachments = value; }

    /**
     * Get the optional character set for your message.
     * @return String
     */
    public String getCharSet() { return charSet; }
    /**
     * Set the optional character set for your message.
     * @param value String
     */
    public void setCharSet(String value) { this.charSet = value; }

    /**
     * Get the list of custom message headers for the message.
     * @return List<CustomHeaderJson>
     */
    public List<CustomHeaderJson> getCustomHeaders() { return customHeaders; }
    /**
     * Set the list of custom message headers for the message.
     * @param value List<CustomHeaderJson>
     */
    public void setCustomHeaders(List<CustomHeaderJson> value) { this.customHeaders = value; }

    /**
     * Get the list of merge data.
     * @return MergeData
     */
    public MergeDataJson getMergeData() { return mergeData; }
    /**
     * Set the list of merge data.
     * @param value MergeData
     */
    public void setMergeData(MergeDataJson value) { this.mergeData = value; }

}
