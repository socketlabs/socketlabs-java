package interfaces;

import models.Attachment;
import models.CustomHeader;

import java.util.List;

public interface MessageBase {
    String getSubject();
    void setSubject(String subject);
    String getPlainTextBody();
    void setPlainTextBody(String plainTextBody);
    String getHtmlBody();
    void setHtmlBody(String htmlBody);
    String getApiTemplate();
    void setApiTemplate(String apiTemplate);
    String getMailingId();
    void setMailingId(String mailingId);
    String getMessageId();
    void setMessageId(String messageId);
    EmailAddress getFrom();
    void setFrom(models.EmailAddress emailAddress);
    EmailAddress getReplyTo();
    void setReplyTo(models.EmailAddress replyTo);
    List<Attachment> getAttachments();
    void setAttachments(List<models.Attachment> attachments);
    String getCharset();
    void setCharset(String charset);
    List<CustomHeader> getCustomHeaders();
    void setCustomHeaders(List<CustomHeader> customheaders);
}
