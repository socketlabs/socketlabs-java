package interfaces;

import models.EmailAddress;

import java.util.List;

public interface BasicMessage extends MessageBase {
    List<EmailAddress> getTo();
    void setTo(List<EmailAddress> to);

    List<EmailAddress> getCc();
    void setCc(List<EmailAddress> cc);

    List<EmailAddress> getBcc();
    void setBcc(List<EmailAddress> bcc);
}
