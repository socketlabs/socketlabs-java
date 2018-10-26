package com.socketLabs.injectionApi;

/**
 * Enumerated result of the client send
 */
public enum SendResult {
    /**
     * 0, An error has occured that was unforeseen
     */
    UnknownError,
    /**
     * 1, A timeout occurred sending the message
     */
    Timeout,
    /**
     * 2, Successful send of message
     */
    Success,
    /**
     * 3, Warnings were found while sending the message
     */
    Warning,
    /**
     * 4, Internal server error
     */
    InternalError,
    /**
     * 5, Message has exceeded the size limit
     */
    MessageTooLarge,
    /**
     * 6, Message exceeded the maximum allowed recipient count
     */
    TooManyRecipients,
    /**
     * 7, The message request contained invalid data
     */
    InvalidData,
    /**
     * 8, The account is over the send quota, rate limit exceeded
     */
    OverQuota,
    /**
     * 9, Too many errors occurred sending the message
     */
    TooManyErrors,
    /**
     * 10, The serverId and apiKey combination is not valid
     */
    InvalidAuthentication,
    /**
     * 11, The account has been disabled
     */
    AccountDisabled,
    /**
     * 12, Too many messages were found on the request
     */
    TooManyMessages,
    /**
     * 13, No valid recipients were found on the message
     */
    NoValidRecipients,
    /**
     * 14, An invalid recipient address was found on the message
     */
    InvalidAddress,
    /**
     * 15, An invalid attachment was found on the message
     */
    InvalidAttachment,
    /**
     * 16, No messages were found on the request
     */
    NoMessages,
    /**
     * 17, No message content was found on the request
     */
    EmptyMessage,
    /**
     * 18, No subject was found on the message
     */
    EmptySubject,
    /**
     * 19, An invalid From address was found on the message
     */
    InvalidFrom,
    /**
     * 20, No To addresses were found on the message
     */
    EmptyToAddress,
    /**
     * 21, No valid message body was found on the message
     */
    NoValidBodyParts,
    /**
     * 22, An invalid templateId was found on the message
     */
    InvalidTemplateId,
    /**
     * 23, The specified templateId has no content associated with it
     */
    TemplateHasNoContent,
    /**
     * 24, A conflict occurred due to the use of templateId and HtmlBody or TextBody
     */
    MessageBodyConflict,
    /**
     * 25, Invalid merge data was found on the message
     */
    InvalidMergeData,
    /**
     * 26, SDK Validation Error: Authentication validation failed, missing or invalid ServerId or ApiKey
     */
    AuthenticationValidationFailed,
    /**
     * 28, SDK Validation Error: No recipients were found in the message
     */
    EmailAddressValidationMissingFrom,
    /**
     * 29, SDK Validation Error: Message is missing From address
     */
    EmailAddressValidationInvalidFrom,
    /**
     * 30, SDK Validation Error: Message is missing To address(es)
     */
    RecipientValidationMaxExceeded,
    /**
     * 31, SDK Validation Error: Message contains an invalid From address
     */
    RecipientValidationNoneInMessage,
    /**
     * 32, SDK Validation Error: Message does not contain a subject
     */
    RecipientValidationMissingTo,
    /**
     * 33, SDK Validation Error: Message does not contain a message body
     */
    RecipientValidationInvalidReplyTo,
    /**
     * 34, SDK Validation Error: Message contains invalid custom headers
     */
    RecipientValidationInvalidRecipients,
    /**
     * 35, SDK Validation Error: Message contains invalid ReplyTo address
     */
    MessageValidationEmptySubject,
    /**
     * 36, SDK Validation Error: Message contains invalid recipients
     */
    MessageValidationEmptyMessage,
    /**
     * 37, SDK Validation Error: Expected messageType of basic or bulk
     */
    MessageValidationInvalidCustomHeaders;

    public static SendResult fromString(String text) {
        for (SendResult b : SendResult.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
