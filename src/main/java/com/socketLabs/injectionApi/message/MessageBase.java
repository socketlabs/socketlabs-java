package com.socketLabs.injectionApi.message;

import javax.annotation.Nullable;
import java.util.List;

public interface MessageBase {

    String getSubject();
    void setSubject(String value);

    String getPlainTextBody();
    void setPlainTextBody(String value);

    String getHtmlBody();
    void setHtmlBody(String htmlBody);

    @Nullable Integer getApiTemplate();
    void setApiTemplate(@Nullable Integer value);

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

    String getCharSet();
    void setCharSet(String value);

    List<CustomHeader> getCustomHeaders();
    void setCustomHeaders(List<CustomHeader> value);
    
}
