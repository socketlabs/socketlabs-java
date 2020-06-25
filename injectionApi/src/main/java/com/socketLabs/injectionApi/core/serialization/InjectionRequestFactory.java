package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socketLabs.injectionApi.message.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Used by the Send function of the SocketLabsClient to generate an InjectionRequest for the Injection Api.
 */
public class InjectionRequestFactory{

    /**
     * Your SocketLabs server ID
     */
    private int serverId;

    /**
     * Your SocketLabs Injection API key
     */
    private String apiKey;

    /**
     * Jackson 2 class used for writing message to JSON
     */
    private ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    /**
     * Creates a new instance of the InjectionRequestFactory.
     * @param serverId Your SocketLabs server ID
     * @param apiKey Your SocketLabs Injection API key
     */
    public InjectionRequestFactory(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    /**
     * Gets the server ID for the Injection Request Factory.
     * @return String
     */
    public int getServerId() {
        return serverId;
    }
    /**
     * Sets the server ID for the Injection Request Factory.
     * @param serverId String
     */
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    /**
     * Gets the SocketLabs Injection API key for the Injection Request Factory.
     * @return String
     */
    public String getApiKey() {
        return apiKey;
    }
    /**
     * Sets the SocketLabs Injection API key for the Injection Request Factory.
     * @param apiKey String
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Generate the InjectionRequest for sending to the Injection Api.
     * @param bulkMessage A BulkMessage object to be sent
     * @return String
     * @throws IOException IOException
     */
    public String GenerateRequest(BulkMessage bulkMessage) throws IOException {
        List<MessageJson> messageJsons = new ArrayList<>();
        MessageJson messageJson = generateBaseMessage(bulkMessage);

        List<AddressJson> to = new ArrayList<>();

        to.add(new AddressJson("%%DeliveryAddress%%", "%%RecipientName%%"));

        messageJson.setTo(to);

        messageJson.setMergeData(populateMergeData(bulkMessage.getGlobalMergeData(), bulkMessage.getTo()));

        messageJsons.add(messageJson);

        return mapper.writeValueAsString(new InjectionRequest(this.serverId, this.apiKey, messageJsons));
    }

    /**
     * Generate the InjectionRequest for sending to the Injection Api.
     * @param basicMessage A BasicMessage object to be sent.
     * @return String
     * @throws IOException IOException
     */
    public String GenerateRequest(BasicMessage basicMessage) throws IOException {

        List<MessageJson> messageJsonList = new ArrayList<>();

        MessageJson messageJson = generateBaseMessage(basicMessage);

        messageJson.setTo(populateEmailList(basicMessage.getTo()));

        messageJson.setCc(populateEmailList(basicMessage.getCc()));

        messageJson.setBcc(populateEmailList(basicMessage.getBcc()));

        messageJsonList.add(messageJson);

        return GetAsJson(new InjectionRequest(this.serverId, this.apiKey, messageJsonList));
    }

    /**
     * Returns an InjectionRequest as JSON to be sent to the Injection API.
     * @param request InjectionRequest
     * @return String
     * @throws IOException IOException
     */
    private String GetAsJson(InjectionRequest request) throws IOException {
        return mapper.writeValueAsString(request);
    }

    /**
     * Converts MessageBase object to a MessageJson object
     * @param messageBase The base message to convert
     * @return MessageJson - JSON serializable representation of MessageBase
     */
    private MessageJson generateBaseMessage(MessageBase messageBase) {
        MessageJson messageJson = new MessageJson();

        messageJson.setSubject(messageBase.getSubject());
        messageJson.setPlainTextBody(messageBase.getPlainTextBody());
        messageJson.setHtmlBody(messageBase.getHtmlBody());
        messageJson.setAmpBody(messageBase.getAmpBody());

        messageJson.setMailingId(messageBase.getMailingId());
        messageJson.setMessageId(messageBase.getMessageId());
        messageJson.setCharSet(messageBase.getCharSet());
        messageJson.setFrom(new AddressJson(messageBase.getFrom().getEmailAddress(), messageBase.getFrom().getFriendlyName()));
        messageJson.setCustomHeaders(populateCustomHeaders(messageBase.getCustomHeaders()));
        messageJson.setAttachments(populateAttachments(messageBase.getAttachments()));

        if (messageBase.getReplyTo() != null) {
            messageJson.setReplyTo(new AddressJson(messageBase.getReplyTo().getEmailAddress(), messageBase.getReplyTo().getFriendlyName()));
        }

        if (messageBase.getApiTemplate() != null) {
            messageJson.setApiTemplate(String.valueOf(messageBase.getApiTemplate()));
        }

        return messageJson;
    }

    /**
     * Converts a List of CustomHeader objects to a List of CustomHeaderJson objects.
     * @param baseCustomHeaders List of CustomHeader objects
     * @return List<CustomHeaderJson>
     */
    private List<CustomHeaderJson> populateCustomHeaders(List<CustomHeader> baseCustomHeaders) {
        if (baseCustomHeaders == null) {
            return null;
        }
        List<CustomHeaderJson> customHeaderJson = new ArrayList<>();

        for (com.socketLabs.injectionApi.message.CustomHeader baseCustomHeader: baseCustomHeaders) {
            customHeaderJson.add(new CustomHeaderJson(baseCustomHeader.getName(), baseCustomHeader.getValue()));
        }
        return customHeaderJson;
    }

    /**
     * Converts a list of Attachment objects to a List of AttachmentJson objects.
     * @param baseAttachments list of Attachment objects
     * @return List<AttachmentJson>
     */
    private List<AttachmentJson> populateAttachments(List<Attachment> baseAttachments) {
        if (baseAttachments == null) {
            return null;
        }
        List<AttachmentJson> attachments = new ArrayList<>();

        for (com.socketLabs.injectionApi.message.Attachment baseAttachment: baseAttachments) {
            AttachmentJson attachment = new AttachmentJson();
            attachment.setContent(baseAttachment.getContent());
            attachment.setContentId(baseAttachment.getContentId());
            attachment.setMimeType(baseAttachment.getMimeType());
            attachment.setName(baseAttachment.getName());
            attachment.setCustomHeaderJsons(populateCustomHeaders(baseAttachment.getCustomHeaders()));
            attachments.add(attachment);
        }

        return attachments;
    }

    /**
     * Converts a List of EmailAddress objects to a List of AddressJson objects.
     * @param baseTo List of EmailAddress objects
     * @return List<AddressJson>
     */
    private List<AddressJson> populateEmailList(List<EmailAddress> baseTo) {
        if (baseTo == null) {
            return null;
        }
        List<AddressJson> addresses = new ArrayList<>();

        for (EmailAddress baseAddress: baseTo) {
            AddressJson address = new AddressJson(baseAddress.getEmailAddress(), baseAddress.getFriendlyName());
            addresses.add(address);
        }

        return addresses;
    }

    /**
     * Loops through a List of BulkRecipient objects,
     * then populates and returns a MergeDataJson object with their merge data.
     * @param global Map<String, String> of global merge data
     * @param recipients List of BulkRecipients
     * @return MergeDataJson
     */
    private MergeDataJson populateMergeData(Map<String, String> global, List<BulkRecipient> recipients) {

        List<List<MergeFieldJson>> perMessageMergeFields = new ArrayList<>();

        for(BulkRecipient recipient : recipients) {
            List<MergeFieldJson> mergeFieldJsonList = generateMergeFieldList(recipient.getMergeData());

            mergeFieldJsonList.add(new MergeFieldJson("DeliveryAddress", recipient.getEmailAddress()));

            if (recipient.getFriendlyName() != null) {
                mergeFieldJsonList.add(new MergeFieldJson("RecipientName", recipient.getFriendlyName()));
            }

            perMessageMergeFields.add(mergeFieldJsonList);
        }

        return new MergeDataJson(perMessageMergeFields, generateMergeFieldList(global));
    }

    /**
     * Converts a HashMap of merge field data into a List of MergeFieldJson objects.
     * @param mergeData Map<String, String> of merge data
     * @return List of MergeFieldJson objects
     */
    private List<MergeFieldJson> generateMergeFieldList(Map<String, String> mergeData) {
        List<MergeFieldJson> mergeFieldJsonList = new ArrayList<>();

        for (Map.Entry<String, String> entry : mergeData.entrySet()) {
            mergeFieldJsonList.add(new MergeFieldJson(entry.getKey(), entry.getValue()));
        }

        return mergeFieldJsonList;
    }
}
