package com.socketLabs.injectionApi;

public enum SendResult {
    UnknownError,
    Timeout,
    Success,
    Warning,
    InternalError,
    MessageTooLarge,
    TooManyRecipients,
    InvalidData,
    OverQuota,
    TooManyErrors,
    InvalidAuthentication,
    AccountDisabled,
    TooManyMessages,
    NoValidRecipients,
    InvalidAddress,
    InvalidAttachment,
    NoMessages,
    EmptyMessage,
    EmptySubject,
    InvalidFrom,
    EmptyToAddress,
    NoValidBodyParts,
    InvalidTemplateId,
    TemplateHasNoContent,
    MessageBodyConflict,
    InvalidMergeData,
    AuthenticationValidationFailed,
    EmailAddressValidationMissingFrom,
    EmailAddressValidationInvalidFrom,
    RecipientValidationMaxExceeded,
    RecipientValidationNoneInMessage,
    RecipientValidationMissingTo,
    RecipientValidationInvalidReplyTo,
    RecipientValidationInvalidRecipients,
    MessageValidationEmptySubject,
    MessageValidationEmptyMessage,
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
