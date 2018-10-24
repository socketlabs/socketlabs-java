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
 * BasicMessage message = new BasicMessage();
 *
 * message.setSubject("Sending A Message");
 * message.setHtmlBody("<html>This is the Html Body of my message.</html>");
 * message.setPlainTextBody("This is the Plain Text Body of my message.");
 *
 * message.setFrom(new EmailAddress("from@example.com"));
 * message.setReplyTo(new EmailAddress("replyto@example.com"));
 *
 * message.getTo().add(new EmailAddress("recipient1@example.com"));
 * message.getTo().add(new EmailAddress("recipient2@example.com", "Recipient #2"));
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


    /**
     * Initializes a new instance of the BasicMessage class
     * @code BasicMessage messsage = new BasicMessage();
     */
    public BasicMessage() {
    }

    /**
     * Gets the list of To recipients.
     * @return {List<EmailAddress>}
     */
    public List<EmailAddress> getTo() { return this.to; }

    /**
     * Sets the list of To recipients.
     * @param value List<EmailAddress>
     */
    public void setTo(List<EmailAddress> value) { this.to = value; }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress String
     */
    public void addToEmailAddress(String emailAddress) {
        this.to.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress String
     * @param name String
     */
    public void addToEmailAddress(String emailAddress, String name) {
        this.to.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress String
     */
    public void addToEmailAddress(EmailAddress emailAddress) {
        this.to.add(emailAddress);
    }

    public List<EmailAddress> getCc() { return this.cc; }
    public void setCc(List<EmailAddress> value) { this.cc = value; }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress String
     */
    public void addCcEmailAddress(String emailAddress) {
        this.cc.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress String}
     * @param name String
     */
    public void addCcEmailAddress(String emailAddress, String name) {
        this.cc.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of CC recipients
     * @param emailAddress String
     */
    public void addCcEmailAddress(EmailAddress emailAddress) {
        this.cc.add(emailAddress);
    }
    
    public List<EmailAddress> getBcc() { return this.bcc; }
    public void setBcc(List<EmailAddress> value) { this.bcc = value; }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress String
     */
    public void addBccEmailAddress(String emailAddress) {
        this.bcc.add(new EmailAddress(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress String
     * @param name String
     */
    public void addBccEmailAddress(String emailAddress, String name) {
        this.bcc.add(new EmailAddress(emailAddress, name ));
    }
    /**
     * Add an EmailAddress to the array of BCC recipients
     * @param emailAddress String
     */
    public void addBccEmailAddress(EmailAddress emailAddress) {
        this.bcc.add(emailAddress);
    }

    /**
     * Gets the instance of the message Subject.
     * @return String
     */
    @Override
    public String getSubject() { return this.subject; }

    /**
     * Sets the instance of the message Subject
     * @param value String
     */
    @Override
    public void setSubject(String value) { this.subject = value; }

    /**
     * Gets the plain text portion of the message body.
     * @return String
     */
    @Override
    public String getPlainTextBody() { return this.plainTextBody; }

    /**
     * Gets the plain text portion of the message body.
     * @param value String
     */
    @Override
    public void setPlainTextBody(String value) { this.plainTextBody = value; }

    /**
     * Gets the HTML portion of the message body.
     * @return String
     */
    @Override
    public String getHtmlBody() { return this.htmlBody; }

    /**
     * Sets the HTML portion of the message body.
     * @param value String
     */
    @Override
    public void setHtmlBody(String value) { this.htmlBody = value; }

    /**
     * Gets the Api Template for the message.
     * @return Integer
     */
    @Override
    public @Nullable Integer getApiTemplate() { return (this.apiTemplate == null) ? null : this.apiTemplate; }

    /**
     * Sets the Api Template for the message.
     * @param value Integer
     */
    @Override
    public void setApiTemplate(@Nullable Integer value) { this.apiTemplate = value; }

    /**
     * Gets the custom MailingId for the message.
     * @return String
     */
    @Override
    public String getMailingId() { return this.mailingId; }

    /**
     * Sets the custom MailingId for the message.
     * @param value String
     */
    @Override
    public void setMailingId(String value) { this.mailingId = value; }

    /**
     * Gets the custom MessageId for the message.
     * @return String
     */
    @Override
    public String getMessageId() { return this.messageId; }

    /**
     * Sets the custom MailingId for the message.
     * @param value String
     */
    @Override
    public void setMessageId(String value) { this.messageId = value; }

    /**
     * Gets the From address.
     * @return EmailAddress
     */
    @Override
    public EmailAddress getFrom() { return this.from; }

    /**
     * Sets the From address.
     * @param value EmailAddress
     */
    @Override
    public void setFrom(EmailAddress value) { this.from = value; }

    /**
     * Gets the Reply To address.
     * @return EmailAddress
     */
    @Override
    public EmailAddress getReplyTo() { return this.replyTo; }

    /**
     * Sets the Reply To address.
     * @param value EmailAddress
     */
    @Override
    public void setReplyTo(EmailAddress value) { this.replyTo = value; }

    /**
     * Gets the list of attachments.
     * @return List<Attachment>
     */
    @Override
    public List<Attachment> getAttachments() { return this.attachments; }

    /**
     * Sets the list of attachments.
     * @param value List<Attachment>
     */
    @Override
    public void setAttachments(List<Attachment> value) { this.attachments = value; }

    /**
     * Add an Attachment to the array of Attachment items
     * @param filePath String
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

    /**
     * Gets the optional character set for your message.
     * @return String
     */
    @Override
    public String getCharset() { return this.charset; }

    /**
     * Sets the optional character set for your message.
     * @param value String
     */
    @Override
    public void setCharset(String value) { this.charset = value; }

    /**
     * Gets the list of custom message headers added to the message.
     * @return List<CustomHeader>
     */
    @Override
    public List<CustomHeader> getCustomHeaders() { return this.customHeaders; }

    /**
     * Sets the list of custom message headers added to the message.
     * @param value List<CustomHeader>
     */
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

    /**
     * Returns the number of recipients and subject for the message, useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Recipients: %d, Subject, %s", this.to.size(), this.subject);
    }
}
