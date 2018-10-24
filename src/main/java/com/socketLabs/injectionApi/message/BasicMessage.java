package com.socketLabs.injectionApi.message;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Basic email message similar to one created in a personal email client such as Outlook.
 * This message can have many recipients of different types, such as To, CC, and BCC.  This
 * message does not support merge fields.
 *
 * Example:
 * var basicMessage = require('./basicMessage');
 * var emailAddress = require('./emailAddress');
 * ...
 *
 * var message = new basicMessage();
 *
 * message.subject = "Sending A Message";
 * message.htmlBody = "<html>This is the Html Body of my message.</html>";
 * message.textBody = "This is the Plain Text Body of my message.";
 *
 * message.from = new emailAddress("from@example.com");
 * message.replyTo = new emailAddress("replyto@example.com");
 *
 * message.to.push(new emailAddress("recipient1@example.com"));
 * message.to.push(new emailAddress("recipient2@example.com", { friendlyName: "Recipient #2" }));
 *
 * message.addToEmailAddress("recipient3@example.com");
 * message.addToEmailAddress("recipient4@example.com", "Recipient #4");
 *
 */
public class BasicMessage implements MessageBase {

    private List<EmailAddress> to = new ArrayList<>();
    private List<EmailAddress> cc = new ArrayList<>();
    private List<EmailAddress> bcc = new ArrayList<>();
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


    public BasicMessage() {
    }

    public List<EmailAddress> getTo() { return this.to; }
    public void setTo(List<EmailAddress> value) { this.to = value; }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress {String}
     */
    public void addToEmailAddress(String emailAddress) {
        this.to.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress {String}
     * @param name {String}
     */
    public void addToEmailAddress(String emailAddress, String name) {
        this.to.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress {String}
     */
    public void addToEmailAddress(EmailAddress emailAddress) {
        this.to.add(emailAddress);
    }

    public List<EmailAddress> getCc() { return this.cc; }
    public void setCc(List<EmailAddress> value) { this.cc = value; }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress {String}
     */
    public void addCcEmailAddress(String emailAddress) {
        this.cc.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress {String} 
     * @param name {String}
     */
    public void addCcEmailAddress(String emailAddress, String name) {
        this.cc.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress {String}
     */
    public void addCcEmailAddress(EmailAddress emailAddress) {
        this.cc.add(emailAddress);
    }
    
    public List<EmailAddress> getBcc() { return this.bcc; }
    public void setBcc(List<EmailAddress> value) { this.bcc = value; }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress {String}
     */
    public void addBccEmailAddress(String emailAddress) {
        this.bcc.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress {String}
     * @param name {String}
     */
    public void addBccEmailAddress(String emailAddress, String name) {
        this.bcc.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress {String}
     */
    public void addBccEmailAddress(EmailAddress emailAddress) {
        this.bcc.add(emailAddress);
    }

    @Override
    public String getSubject() { return this.subject; }
    @Override
    public void setSubject(String value) { this.subject = value; }

    @Override
    public String getPlainTextBody() { return this.plainTextBody; }
    @Override
    public void setPlainTextBody(String value) { this.plainTextBody = value; }

    @Override
    public String getHtmlBody() { return this.htmlBody; }
    @Override
    public void setHtmlBody(String value) { this.htmlBody = value; }

    @Override
    public @Nullable Integer getApiTemplate() { return (this.apiTemplate == null) ? null : this.apiTemplate; }
    @Override
    public void setApiTemplate(@Nullable Integer value) { this.apiTemplate = value; }

    @Override
    public String getMailingId() { return this.mailingId; }
    @Override
    public void setMailingId(String value) { this.mailingId = value; }

    @Override
    public String getMessageId() { return this.messageId; }
    @Override
    public void setMessageId(String value) { this.messageId = value; }

    @Override
    public EmailAddress getFrom() { return this.from; }
    @Override
    public void setFrom(EmailAddress value) { this.from = value; }

    @Override
    public EmailAddress getReplyTo() { return this.replyTo; }
    @Override
    public void setReplyTo(EmailAddress value) { this.replyTo = value; }

    @Override
    public List<Attachment> getAttachments() { return this.attachments; }
    @Override
    public void setAttachments(List<Attachment> value) { this.attachments = value; }
    /**
     * Add an attachment to the array of Attachment items using the filePath
     * @param filePath The path to your attachment on your local system.
     * @throws IOException
     */
    public void addAttachments(String filePath) throws IOException {
        this.attachments.add(new Attachment(filePath));
    }
    /**
     * Add an Attachment to the array of Attachment items
     * @param attachment
     * @throws IOException
     */
    public void addAttachments(Attachment attachment) throws IOException {
        this.attachments.add(attachment);
    }

    @Override
    public String getCharset() { return this.charset; }
    @Override
    public void setCharset(String value) { this.charset = value; }

    @Override
    public List<CustomHeader> getCustomHeaders() { return this.customHeaders; }
    @Override
    public void setCustomHeaders(List<CustomHeader> value) { this.customHeaders = value; }
    /**
     * Add a CustomHeaderJson to the message
     * @param name {String}
     * @param value {String}
     */
    public void addCustomHeader(String name, String value) {
        this.customHeaders.add(new CustomHeader(name, value ));
    }
    /**
     * Add a CustomHeaderJson to the message
     * @param header {CustomHeaderJson}
     */
    public void addCustomHeader(CustomHeader header) {
        this.customHeaders.add(header);
    }

    @Override
    public String toString() {
        return String.format("Recipients: %d, Subject, %s", this.to.size(), this.subject);
    }
}
