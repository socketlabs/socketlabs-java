package com.socketLabs.injectionApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SendResponse {

    @JsonProperty("ErrorCode")
    private SendResult result;

    @JsonProperty("TransactionReceipt")
    private String transactionReceipt;

    @JsonProperty("MessageResults")
    private List<AddressResult> addressResults;

    public SendResponse() {
    }
    public SendResponse(SendResult result) {
        this.result = result;
        this.transactionReceipt = null;
        this.addressResults = null;
    }
    public SendResponse(SendResult result, List<AddressResult> addressResults) {
        this.result = result;
        this.transactionReceipt = null;
        this.addressResults = addressResults;
    }
    public SendResponse(SendResult result, String transactionReceipt, List<AddressResult> addressResults) {
        this.result = result;
        this.transactionReceipt = transactionReceipt;
        this.addressResults = addressResults;
    }

    public SendResult getResult() {
        return result;
    }
    public void setResult(SendResult value) {
        this.result = value;
    }

    public String getTransactionReceipt() {
        return transactionReceipt;
    }
    public void setTransactionReceipt(String value) {
        this.transactionReceipt = value;
    }

    public List<AddressResult> getAddressResults() {
        return addressResults;
    }
    public void setAddressResults(List<AddressResult> value) {
        this.addressResults = value;
    }

    public String getResponseMessage() {

        switch (this.result) {
            case UnknownError:
                return "An error has occured that was unforeseen";

            case Timeout:
                return "A timeout occurred sending the message";

            case Success:
                return "Successful send of message";

            case Warning:
                return "Warnings were found while sending the message";

            case InternalError:
                return "Internal server error";

            case MessageTooLarge:
                return "Message size has exceeded the size limit";

            case TooManyRecipients:
                return "Message exceeded maximum recipient count in the message";

            case InvalidData:
                return "Invalid data was found on the message";

            case OverQuota:
                return "The account is over the send quota, rate limit exceeded";

            case TooManyErrors:
                return "Too many errors occurred sending the message";

            case InvalidAuthentication:
                return "The ServerId/ApiKey combination is invalid";

            case AccountDisabled:
                return "The account has been disabled";

            case TooManyMessages:
                return "Too many messages were found in the request";

            case NoValidRecipients:
                return "No valid recipients were found in the message";

            case InvalidAddress:
                return "An invalid recipient were found on the message";

            case InvalidAttachment:
                return "An invalid attachment were found on the message";

            case NoMessages:
                return "No message body was found in the message";

            case EmptyMessage:
                return "No message body was found in the message";

            case EmptySubject:
                return "No subject was found in the message";

            case InvalidFrom:
                return "An invalid from address was found on the message";

            case EmptyToAddress:
                return "No To addresses were found in the message";

            case NoValidBodyParts:
                return "No valid message body was found in the message";

            case InvalidTemplateId:
                return "An invalid TemplateId was found in the message";

            case TemplateHasNoContent:
                return "The specified TemplateId has no content for the message";

            case MessageBodyConflict:
                return "A conflict occurred on the message body of the message";

            case InvalidMergeData:
                return "Invalid MergeDataJson was found on the message";

            case AuthenticationValidationFailed:
                return "SDK Validation Error : Authentication Validation Failed, Missing or invalid ServerId or ApiKey";

            case RecipientValidationMaxExceeded:
                return "SDK Validation Error : Message exceeded maximum recipient count in the message";

            case RecipientValidationNoneInMessage:
                return "SDK Validation Error : No Recipients were found in the message";

            case EmailAddressValidationMissingFrom:
                return "SDK Validation Error : From email address is missing in the message";

            case RecipientValidationMissingTo:
                return "SDK Validation Error : To addresses are missing in the message";

            case EmailAddressValidationInvalidFrom:
                return "SDK Validation Error : From email address in the message is invalid";

            case MessageValidationEmptySubject:
                return "SDK Validation Error : No Subject was found in the message";

            case MessageValidationEmptyMessage:
                return "SDK Validation Error : No message body was found in the message";

            case MessageValidationInvalidCustomHeaders:
                return "SDK Validation Error : Invalid Custom Headers were found in the message";

            case RecipientValidationInvalidReplyTo:
                return "SDK Validation Error : Invalid ReplyTo AddressJson was found in the message";

            case RecipientValidationInvalidRecipients:
                return "SDK Validation Error : Invalid recipients were found in the message";

            default:
                return "";
        }
    }

}
