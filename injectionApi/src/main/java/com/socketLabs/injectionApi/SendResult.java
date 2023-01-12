package com.socketLabs.injectionApi;

/**
 * Enumerated result of the client send
 */
public enum SendResult {
    /** An error has occured that was unforeseen */
    UnknownError,
    /** A timeout occurred sending the message */
    Timeout,
    /** Successful send of message */
    Success,
    /** Warnings were found while sending the message */
    Warning,
    /** Internal server error */
    InternalError,
    /** Message has exceeded the size limit */
    MessageTooLarge,
    /** Message exceeded the maximum allowed recipient count */
    TooManyRecipients,
    /** The message request contained invalid data */
    InvalidData,
    /** The account is over the send quota, rate limit exceeded */
    OverQuota,
    /** Too many errors occurred sending the message */
    TooManyErrors,
    /** The serverId and apiKey combination is not valid */
    InvalidAuthentication,
    /** The account has been disabled */
    AccountDisabled,
    /** Too many messages were found on the request */
    TooManyMessages,
    /** No valid recipients were found on the message */
    NoValidRecipients,
    /** An invalid recipient address was found on the message */
    InvalidAddress,
    /** An invalid attachment was found on the message */
    InvalidAttachment,
    /** No messages were found on the request */
    NoMessages,
    /** No message content was found on the request */
    EmptyMessage,
    /** No subject was found on the message */
    EmptySubject,
    /** An invalid From address was found on the message */
    InvalidFrom,
    /** No To addresses were found on the message */
    EmptyToAddress,
    /** No valid message body was found on the message */
    NoValidBodyParts,
    /** An invalid templateId was found on the message */
    InvalidTemplateId,
    /** The specified templateId has no content associated with it */
    TemplateHasNoContent,
    /** A conflict occurred due to the use of templateId and HtmlBody or TextBody */
    MessageBodyConflict,
    /** Invalid merge data was found on the message */
    InvalidMergeData,
    /** SDK Validation Error: Authentication validation failed, missing or invalid ServerId or ApiKey */
    AuthenticationValidationFailed,
    /** SDK Validation Error: No recipients were found in the message  */
    EmailAddressValidationMissingFrom,
    /** SDK Validation Error: Message is missing From address */
    EmailAddressValidationInvalidFrom,
    /** SDK Validation Error: Message is missing To address(es) */
    RecipientValidationMaxExceeded,
    /** SDK Validation Error: Message contains an invalid From address */
    RecipientValidationNoneInMessage,
    /** SDK Validation Error: Message does not contain a subject */
    RecipientValidationMissingTo,
    /** SDK Validation Error: Message does not contain a message body */
    RecipientValidationInvalidReplyTo,
    /** SDK Validation Error: Message contains invalid custom headers */
    RecipientValidationInvalidRecipients,
    /** SDK Validation Error: Message contains invalid ReplyTo address */
    MessageValidationEmptySubject,
    /** SDK Validation Error: Message contains invalid recipients */
    MessageValidationEmptyMessage,
    /** SDK Validation Error: Expected messageType of basic or bulk */
    MessageValidationInvalidCustomHeaders,
    /** SDK Validation Error: Message contains invalid metadata */
    MessageValidationInvalidMetadata;

    /**
     * Convert a String into a SendResult enum
     * @param text String
     * @return SendResult
     */
    public static SendResult fromString(String text) {
        for (SendResult b : SendResult.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
