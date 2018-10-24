package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.AddressResult;
import com.socketLabs.injectionApi.SendResponse;
import com.google.common.base.Strings;
import com.socketLabs.injectionApi.SendResult;
import com.socketLabs.injectionApi.models.*;

import java.util.ArrayList;
import java.util.List;

public class SendValidator {

    private int MaximumRecipientsPerMessage = 50;

    /**
     * Validate the ServerId and Api Key pair prior before sending to the Injection API.
     * @param serverId Your SocketLabs ServerId number.
     * @param apiKey Your SocketLabs Injection API key.
     * @return A SendResponse with the validation results
     */
    public SendResponse ValidateCredentials(int serverId, String apiKey) {

        if (Strings.isNullOrEmpty(apiKey))
            return new SendResponse(SendResult.AuthenticationValidationFailed);

        if (serverId <= 0)
            return new SendResponse(SendResult.AuthenticationValidationFailed);

        return new SendResponse(SendResult.Success);

    }

    /**
     * Validate a basic email message before sending to the Injection API.
     * @param message An BasicMessage object to be sent.
     * @return A SendResponse with the validation results
     */
    public SendResponse ValidateMessage(BasicMessage message)
    {

        SendResult result = ValidateMessageBase(message);
        if (result == SendResult.Success)
            return ValidateRecipients(message);

        return new SendResponse(result);
    }

    /**
     *Validate a bulk email message before sending to the Injection API.
     * @param message An BulkMessage object to be sent.
     * @return A SendResponse with the validation results
     */
    public SendResponse ValidateMessage(BulkMessage message)
    {
        SendResult result = ValidateMessageBase(message);
        if (result == SendResult.Success)
            return ValidateRecipients(message);

        return new SendResponse(result);
    }

    /**
     * Validate the required fields of a message. Fields validated are Subject, From Address, Reply To (if set), Message Body, and Custom Headers (if set)
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return The validation result as SendResult
     */
    private SendResult ValidateMessageBase(MessageBase message)
    {
        if (!HasSubject(message)) return SendResult.MessageValidationEmptySubject;

        if (!HasFromAddress(message)) return SendResult.EmailAddressValidationMissingFrom;
        if (!message.getFrom().isValid()) return SendResult.EmailAddressValidationInvalidFrom;

        if (!HasValidReplyTo(message)) return SendResult.RecipientValidationInvalidReplyTo;

        if (!HasMessageBody(message)) return SendResult.MessageValidationEmptyMessage;

        if (message.getCustomHeaders() != null && !message.getCustomHeaders().isEmpty())
            if (!HasValidCustomHeaders(message.getCustomHeaders())) return SendResult.MessageValidationInvalidCustomHeaders;

        return SendResult.Success;
    }

    
    /**
     * Check if the message has a subject
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return boolean result
     */
    private boolean HasSubject(MessageBase message)
    {
        return !Strings.isNullOrEmpty(message.getSubject());
    }

    /**
     * Check if the message has a valid From Email Address
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return boolean result
     */
    private boolean HasFromAddress(MessageBase message)
    {
        if (message.getFrom() == null)
            return false;

        return (!Strings.isNullOrEmpty(message.getFrom().getEmailAddress()));
    }

    /**
     * Check if the message has a Message Body.
     * If an Api Template is specified it will override the HtmlBody and/or the PlainTextBody.
     * If no Api Template is specified the HtmlBody and/or the PlainTextBody must me set
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return boolean result
     */
    private boolean HasMessageBody(MessageBase message)
    {
        boolean hasApiTemplate = HasApiTemplate(message);
        if (hasApiTemplate) return true;

        boolean hasHtmlBody = !Strings.isNullOrEmpty(message.getHtmlBody());
        boolean hasPlainTextBody = !Strings.isNullOrEmpty(message.getPlainTextBody());

        return (hasHtmlBody || hasPlainTextBody);
    }

    /**
     * Check if an ApiTemplate was specified and is valid.
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return boolean result
     */
    private boolean HasApiTemplate(MessageBase message)
    {
        return (message.getApiTemplate() == null || message.getApiTemplate() != 0);
    }

    /**
     * If set, check if a ReplyTo email address is valid
     * @param message The base interface, MessageBase, of the message to be sent.
     * @return boolean result
     */
    private boolean HasValidReplyTo(MessageBase message)
    {
        if (Strings.isNullOrEmpty(message.getReplyTo().getEmailAddress()) && Strings.isNullOrEmpty(message.getReplyTo().getFriendlyName())) return true;
        return message.getReplyTo().isValid();
    }


    /**
    * Validate email recipients for a basic message
    * Checks the To, Cc, and the Bcc recipient fields (List of IEmailAddress) for the following:
    *   > At least 1 recipient is in the list.
    *   > Cumulative count of recipients in all 3 lists do not exceed the MaximumRecipientsPerMessage.
    *   > Recipients in lists are valid.
    *  If errors are found, the SendResponse will contain the invalid email addresses
     * @param message An BasicMessage object to be sent.
     * @return A SendResponse with the validation results.
     */
    private SendResponse ValidateRecipients(BasicMessage message)
    {
        int fullRecipientCount = GetFullRecipientCount(message);
        if (fullRecipientCount <= 0) return new SendResponse(SendResult.RecipientValidationNoneInMessage);

        if (fullRecipientCount > MaximumRecipientsPerMessage) return new SendResponse(SendResult.RecipientValidationMaxExceeded);

        List<AddressResult> invalidRec = HasInvalidRecipients(message);
        if (invalidRec != null && !invalidRec.isEmpty())
            return new SendResponse(SendResult.RecipientValidationInvalidRecipients, invalidRec);

        return new SendResponse(SendResult.Success);
    }

    /**
     * Validate email recipients for a bulk message
     * Checks the To recipient field (List of IBulkRecipient) for the following:
     *   > At least 1 recipient is in the list.
     *   > Cumulative count of recipients in all 3 lists do not exceed the MaximumRecipientsPerMessage.
     *   > Recipients in lists are valid.
     *  If errors are found, the SendResponse will contain the invalid email addresses

     * @param message An BulkMessage object to be sent.
     * @return A SendResponse with the validation results.
     */
    private SendResponse ValidateRecipients(BulkMessage message)
    {
        if (message.getTo() == null || message.getTo().size() <= 0) return new SendResponse(SendResult.RecipientValidationMissingTo);

        if (message.getTo().size() > MaximumRecipientsPerMessage) return new SendResponse(SendResult.RecipientValidationMaxExceeded);

        List<AddressResult> invalidRec = HasInvalidRecipients(message);
        if (invalidRec != null && !invalidRec.isEmpty())
            return new SendResponse(SendResult.RecipientValidationInvalidRecipients, invalidRec);

        return new SendResponse(SendResult.Success);
    }

    /**
     * Cumulative count of recipients in all 3 recipient lists To, Cc, and Bcc (List of EmailAddress)
     * @param message An BasicMessage object to be sent.
     * @return an int count of recipients
     */
    private int GetFullRecipientCount(BasicMessage message)
    {
        int recipientCount = 0;
        if (message.getTo() != null)
            recipientCount += message.getTo().size();
        if (message.getCc() != null)
            recipientCount += message.getCc().size();
        if (message.getBcc() != null)
            recipientCount += message.getBcc().size();
        return recipientCount;
    }


    /**
     * Check all 3 recipient lists To, Cc, and Bcc (List of EmailAddress) for valid email addresses
     * @param message An BasicMessage object to be sent.
     * @return A List of AddressResult if an invalid email address is found.
     */
    private List<AddressResult> HasInvalidRecipients(BasicMessage message)
    {
        List<AddressResult> result = new ArrayList<>();

        List<AddressResult>  invalidTo = FindInvalidEmailAddresses(message.getTo());
        if (invalidTo != null && !invalidTo.isEmpty())
            result.addAll(invalidTo);

        List<AddressResult>  invalidCc = FindInvalidEmailAddresses(message.getCc());
        if (invalidCc != null && !invalidCc.isEmpty())
            result.addAll(invalidCc);

        List<AddressResult>  invalidBcc = FindInvalidEmailAddresses(message.getBcc());
        if (invalidBcc != null && !invalidBcc.isEmpty())
            result.addAll(invalidBcc);

        return result;
    }

    /**
     * Check the To recipient list (List of BulkMessage) for valid email addresses.
     * @param message An BulkMessage object to be sent.
     * @return A List of AddressResult if an invalid email address is found.
     */
    private List<AddressResult> HasInvalidRecipients(BulkMessage message)
    {
        List<AddressResult> result = new ArrayList<>();

        List<AddressResult>  invalidTo = FindInvalidRecipients(message.getTo());
        if (invalidTo != null && !invalidTo.isEmpty())
            result.addAll(invalidTo);

        return result;
    }

    /**
     * Check List<EmailAddress> for valid email addresses
     * @param recipients List<IEmailAddress> to validate
     * @return A List<AddressResult> if an invalid email address is found.
     */
    private List<AddressResult> FindInvalidEmailAddresses(List<EmailAddress> recipients)
    {
        List<AddressResult> invalid = new ArrayList<>();
        for(EmailAddress r: recipients) {
            if (!r.isValid()) {
                invalid.add(new AddressResult(r.getEmailAddress(), false, "InvalidAddress"));
            }
        }

         return !invalid.isEmpty()? invalid: null;
    }

    /**
     * Check List<BulkRecipient> for valid email addresses
     * @param recipients List<BulkRecipient> to validate
     * @return A List<AddressResult> if an invalid email address is found.
     */
    private List<AddressResult> FindInvalidRecipients(List<BulkRecipient> recipients)
    {
        List<AddressResult> invalid = new ArrayList<>();
        for(BulkRecipient r: recipients) {
            if (!r.isValid()) {
                invalid.add(new AddressResult(r.getEmailAddress(), false, "InvalidAddress"));
            }
        }

        return !invalid.isEmpty()? invalid: null;
    }

    /**
     * Check if ICustomHeader in List are valid.
     * @param customHeaders List<CustomHeader> to validate
     * @return boolean result
     */
    private boolean HasValidCustomHeaders(List<CustomHeader> customHeaders)
    {
        for (CustomHeader c : customHeaders) {
            if (c.isValid()) return false;
        }
        return true;
    }

}
