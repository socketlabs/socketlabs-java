package com.socketLabs.injectionApi.message;

import javax.annotation.Nullable;
import java.util.List;

/**
 * The MessageBase is an interface that contains fields used by the Injection API and is implemented by all message types.
 */
public interface MessageBase {

    /**
     * Get the instance of the message Subject.
     * @return String
     */
    String getSubject();
    /**
     * Set the instance of the message Subject.
     * @param value String
     */
    void setSubject(String value);

    /**
     * Get the plain text portion of the message body.
     * @return String
     */
    String getPlainTextBody();
    /**
     * Set the plain text portion of the message body.
     * @param value String
     */
    void setPlainTextBody(String value);

    /**
     * Get the HTML portion of the message body.
     * @return String
     */
    String getHtmlBody();
    /**
     * Set the HTML portion of the message body.
     * @param value String
     */
    void setHtmlBody(String value);

    /**
     * Get the Api Template for the message.
     * @return String
     */
    @Nullable Integer getApiTemplate();
    /**
     * Set the Api Template for the message.
     * @param value String
     */
    void setApiTemplate(@Nullable Integer value);

    /**
     * Get the custom MailingId for the message.     *
     * @return String String
     */
    String getMailingId();
    /**
     * Set the custom MailingId for the message.
     * @param value String String
     */
    void setMailingId(String value);

    /**
     * Get the custom MessageId for the message.
     * @return String String
     */
    String getMessageId();
    /**
     * Set the custom MessageId for the message.
     * @param value String
     */
    void setMessageId(String value);

    /**
     * Get the From address.
     * @return EmailAddress
     */
    EmailAddress getFrom();
    /**
     * Set the From address.
     * @param value EmailAddress
     */
    void setFrom(EmailAddress value);

    /**
     * Get the optional ReplyTo address for the message.
     * @return EmailAddress
     */
    @Nullable EmailAddress getReplyTo();
    /**
     * Set the optional ReplyTo address for the message.
     * @param value EmailAddress
     */
    void setReplyTo(@Nullable EmailAddress value);

    /**
     * Get the list of attachments.
     * @return {@code List<Attachment>}
     */
    List<Attachment> getAttachments();
    /**
     * Set the list of attachments.
     * @param value {@code List<Attachment>}
     */
    void setAttachments(List<Attachment> value);

    /**
     * Get the optional character set for your message. - Default is UTF8
     * @return String
     */
    String getCharSet();
    /**
     * Set the optional character set for your message. - Default is UTF8
     * @param value String
     */
    void setCharSet(String value);

    /**
     * Get the list of custom message headers added to the message.
     * @return {@code List<CustomHeader>}
     */
    List<CustomHeader> getCustomHeaders();
    /**
     * Set the list of custom message headers added to the message.
     * @param value {@code List<CustomHeader>}
     */
    void setCustomHeaders(List<CustomHeader> value);
    
}
