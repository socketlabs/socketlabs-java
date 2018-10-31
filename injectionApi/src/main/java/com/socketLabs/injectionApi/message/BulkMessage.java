package com.socketLabs.injectionApi.message;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;

/**
 * A bulk message usually contains a single recipient per message
 * and is generally used to send the same content to many recipients,
 * optionally customizing the message via the use of Merge Data.
 *<p>
 * Example:
 * <pre>
 *     BulkMessage message = new BulkMessage();
 *
 *     message.setPlainTextBody("This is the body of my message sent to ##Name##");
 *     message.setHtmlBody("&#60;html&#62;This is the HtmlBody of my message sent to ##Name##&#60;/html&#62;");
 *     message.setSubject("Sending a test message");
 *
 *     message.setFrom(new EmailAddress("from@example.com"));
 *     message.setReplyTo(new EmailAddress("replyto@example.com"));;
 *
 *     message.addToRecipient("recipient1@example.com");
 *     message.getTo().add(new BulkRecipient("recipient2@example.com", "Recipient #2"));
 *
 *     message.addMergeData("name1", "value1");
 *     message.addMergeData("name2", "value2");
 * </pre>
 * </p>
 */
public class BulkMessage implements MessageBase {

    /**
     * The list of To recipients.
     */
    private List<BulkRecipient> to = new ArrayList<>();
    /**
     * A map containing Merge Data items that will be global across the whole message.
     * <p>
     *     (Optional)
     * </p>
     */
    private TreeMap<String, String> globalMergeData = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    /**
     * The message Subject.
     */
    private String subject;
    /**
     * The plain text portion of the message body.
     * <p>
     *     (Optional)
     *     Either PlainTextBody or HtmlBody must be used or use a ApiTemplate
     * </p>
     */
    private String plainTextBody;
    /**
     * The HTML portion of the message body.
     * <p>
     *     (Optional)
     *     Either PlainTextBody or HtmlBody must be used or use a ApiTemplate
     * </p>
     */
    private String htmlBody;
    /**
     * The Api Template for the message.
     * <p>
     *     (Optional)
     *     Either PlainTextBody or HtmlBody must be used or use a ApiTemplate
     * </p>
     */
    private @Nullable Integer apiTemplate;
    /**
     * The custom MailingId for the message.
     * <p>
     *     (Optional)
     *     See https://www.socketlabs.com/blog/best-practices-for-using-custom-mailingids-and-messageids/ for more information.
     * </p>
     */
    private String mailingId;
    /**
     * The custom MessageId for the message.
     * <p>
     *     (Optional)
     * </p>
     */
    private String messageId;
    /**
     * The From address.
     */
    private EmailAddress from;
    /**
     * The ReplyTo address for the message.
     * <p>
     *     (Optional)
     * </p>
     */
    private EmailAddress replyTo;
    /**
     * The list of attachments.
     * <p>
     *     (Optional)
     * </p>
     */
    private List<Attachment> attachments = new ArrayList<>();
    /**
     * The optional character set for your message.
     * <p>
     *     (Optional)
     *     Default is UTF8
     * </p>
     */
    private String charSet;
    /**
     * A list of custom message headers added to the message.
     * <p>
     *     (Optional)
     * </p>
     */
    private List<CustomHeader> customHeaders = new ArrayList<>();


    /**
     * Initializes a new instance of the BasicMessage class
     * @code BasicMessage messsage = new BasicMessage();
     */
    public BulkMessage() {
    }

    /**
     * Gets the list of To recipients.
     * @return List<BulkRecipient>
     */
    public List<BulkRecipient> getTo() { return this.to; }
    /**
     * Sets the list of To recipients.
     * @param value List<BulkRecipient>
     */
    public void setTo(List<BulkRecipient> value) { this.to = value; }
    /**
     * Add an BulkRecipient to the array of To recipients
     * @param emailAddress String
     */
    public void addToRecipient(String emailAddress) {
        this.to.add(new BulkRecipient(emailAddress));
    }
    /**
     * Add an EmailAddress to the array of To recipients
     * @param emailAddress String
     * @param name String
     */
    public void addToRecipient(String emailAddress, String name) {
        this.to.add(new BulkRecipient(emailAddress, name ));
    }
    /**
     * Add an BulkRecipient to the array of To recipients
     * @param bulkRecipient String
     */
    public void addToRecipient(BulkRecipient bulkRecipient) {
        this.to.add(bulkRecipient);
    }


    /**
     * Get the message Subject.
     * @return String
     */
    @Override
    public String getSubject() {
        return this.subject;
    }
    /**
     * Set the message Subject.
     * @param value String
     */
    @Override
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Get the plain text portion of the message body.
     * @return String
     */
    @Override
    public String getPlainTextBody() {
        return this.plainTextBody;
    }
    /**
     * Set the plain text portion of the message body.
     * @param value String
     */
    @Override
    public void setPlainTextBody(String value) {
        this.plainTextBody = value;
    }

    /**
     * Get the HTML portion of the message body.
     * @return String
     */
    @Override
    public String getHtmlBody() {
        return this.htmlBody;
    }
    /**
     * Set the HTML portion of the message body.
     * @param value String
     */
    @Override
    public void setHtmlBody(String value) {
        this.htmlBody = value;
    }

    /**
     * Get the Api Template for the message.
     * @return @Nullable Integer
     */
    @Override
    public @Nullable Integer getApiTemplate() {
        return this.apiTemplate;
    }
    /**
     * Set the Api Template for the message.
     * @param value @Nullable Integer
     */
    @Override
    public void setApiTemplate(@Nullable Integer value) {
        this.apiTemplate = value;
    }

    /**
     * Get the custom MailingId for the message.
     * @return String
     */
    @Override
    public String getMailingId() {
        return this.mailingId;
    }
    /**
     * Set the custom MailingId for the message.
     * @param value String
     */
    @Override
    public void setMailingId(String value) {
        this.mailingId = value;
    }

    /**
     * Get the custom MessageId for the message.
     * @return String
     */
    @Override
    public String getMessageId() {
        return this.messageId;
    }
    /**
     * Set the custom MessageId for the message.
     * @param value String
     */
    @Override
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Get the From address.
     * @return EmailAddress
     */
    @Override
    public EmailAddress getFrom() {
        return this.from;
    }
    /**
     * Set the From address.
     * @param value EmailAddress
     */
    @Override
    public void setFrom(EmailAddress value) { this.from = value; }

    /**
     * Get the ReplyTo address for the message.
     * @return EmailAddress
     */
    @Override
    public EmailAddress getReplyTo() {
        return this.replyTo;
    }
    /**
     * Set the ReplyTo address for the message.
     * @param value EmailAddress
     */
    @Override
    public void setReplyTo(EmailAddress value) { this.replyTo = value; }

    /**
     * Get the list of attachments.
     * @return List<Attachment>
     */
    @Override
    public List<Attachment> getAttachments() {
        return this.attachments;
    }
    /**
     * Set the list of attachments.
     * @param value List<Attachment>
     */
    @Override
    public void setAttachments(List<Attachment> value) { this.attachments = value; }
    /**
     * Add an Attachment to the array of Attachment items
     * @param fileName String
     * @throws IOException IOException
     */
    public void addAttachments(String fileName) throws IOException {
        this.attachments.add(new Attachment(fileName));
    }
    /**
     * Add an Attachment to the array of Attachment items
     * @param attachment Attachment
     */
    public void addAttachments(Attachment attachment) {
        this.attachments.add(attachment);
    }

    /**
     * Gets the optional character set for your message.
     * @return String
     */
    @Override
    public String getCharSet() { return this.charSet; }

    /**
     * Sets the optional character set for your message.
     * @param value String
     */
    @Override
    public void setCharSet(String value) { this.charSet = value; }

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
     * Add a CustomHeader to the message
     * @param name {String}
     * @param value {String}
     */
    public void addCustomHeader(String name, String value) {
        this.customHeaders.add(new CustomHeader(name, value ));
    }
    /**
     * Add a CustomHeader to the message
     * @param header {CustomHeaderJson}
     */
    public void addCustomHeader(CustomHeader header) {
        this.customHeaders.add(header);
    }


    /**
     * Get the map containing Merge Data items that will be global across the whole message.
     * @return TreeMap<String, String>
     */
    public TreeMap<String, String> getGlobalMergeData() { return globalMergeData; }
    /**
     * Set the map containing Merge Data items that will be global across the whole message.
     * @param value TreeMap<String, String>
     */
    public void setGlobalMergeData(TreeMap<String, String> value) { this.globalMergeData = value; }
    /**
     * Add a Global Merge Data entry to the message
     * @param field String
     * @param value String
     */
    public void addGlobalMergeData(String field, String value) {
        this.globalMergeData.put(field, value);
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
