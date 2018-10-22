package com.socketLabs.models;

import java.util.List;

public interface MessageBase {

    String getSubject();
    void setSubject(String value);

    String getPlainTextBody();
    void setPlainTextBody(String value);

    String getHtmlBody();
    void setHtmlBody(String htmlBody);

    String getApiTemplate();
    void setApiTemplate(String value);

    String getMailingId();
    void setMailingId(String value);

    String getMessageId();
    void setMessageId(String value);

    EmailAddress getFrom();
    void setFrom(EmailAddress value);

    EmailAddress getReplyTo();
    void setReplyTo(EmailAddress value);

    List<Attachment> getAttachments();
    void setAttachments(List<Attachment> value);

    String getCharset();
    void setCharset(String value);

    List<CustomHeader> getCustomHeaders();
    void setCustomHeaders(List<CustomHeader> value);
    
}
