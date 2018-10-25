package com.socketLabs.injectionApi.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.message.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InjectionRequestFactory{

    private int serverId;

    private String apiKey;

    // TODO: remove .enable(SerializationFeature.INDENT_OUTPUT) (used for debugging)
    private ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public InjectionRequestFactory(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    public int getServerId() {
        return serverId;
    }
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String GenerateRequest(BulkMessage bulkMessage) throws IOException {
        List<MessageJson> messageJsons = new ArrayList<>();
        MessageJson messageJson = generateBaseMessage(bulkMessage);

        List<AddressJson> to = new ArrayList<>();

        to.add(new AddressJson("%%DeliveryAddress%%", "%%RecipientName%%"));

        messageJson.setTo(to);

        messageJson.setMergeDataJson(populateMergeData(bulkMessage.getMergeData(), bulkMessage.getTo()));

        messageJsons.add(messageJson);

        return mapper.writeValueAsString(new InjectionRequest(this.serverId, this.apiKey, messageJsons));
    }

    public String GenerateRequest(BasicMessage basicMessage) throws IOException {

        List<MessageJson> messageJsons = new ArrayList<>();

        MessageJson messageJson = generateBaseMessage(basicMessage);

        messageJson.setTo(populateTo(basicMessage.getTo()));

        messageJsons.add(messageJson);

        return GetAsJson(new InjectionRequest(this.serverId, this.apiKey, messageJsons));
    }

    private String GetAsJson(InjectionRequest request) throws IOException {
        return mapper.writeValueAsString(request);
    }

    private MessageJson generateBaseMessage(MessageBase messageBase) {
        MessageJson messageJson = new MessageJson();

        messageJson.setSubject(messageBase.getSubject());
        messageJson.setPlainTextBody(messageBase.getPlainTextBody());
        messageJson.setHtmlBody(messageBase.getHtmlBody());
        messageJson.setMailingId(messageBase.getMailingId());
        messageJson.setMessageId(messageBase.getMessageId());
        messageJson.setCharSet(messageBase.getCharSet());
        messageJson.setFrom(new AddressJson(messageBase.getFrom().getEmailAddress(), messageBase.getFrom().getFriendlyName()));
        messageJson.setCustomHeaders(populateCustomHeaders(messageBase.getCustomHeaders()));
        messageJson.setAttachments(populateAttachments(messageBase.getAttachments()));

        if (messageBase.getApiTemplate() != null) {
            messageJson.setApiTemplate(String.valueOf(messageBase.getApiTemplate()));
        }

        return messageJson;
    }

    // TODO: Some of these methods are really similar so maybe we should look at refactoring

    private List<CustomHeaderJson> populateCustomHeaders(List<com.socketLabs.injectionApi.message.CustomHeader> baseCustomHeaders) {
        if (baseCustomHeaders == null) {
            return null;
        }
        List<CustomHeaderJson> customHeaderJsons = new ArrayList<>();

        for (com.socketLabs.injectionApi.message.CustomHeader baseCustomHeader: baseCustomHeaders) {
            customHeaderJsons.add(new CustomHeaderJson(baseCustomHeader.getName(), baseCustomHeader.getValue()));
        }
        return customHeaderJsons;
    }

    private List<AttachmentJson> populateAttachments(List<com.socketLabs.injectionApi.message.Attachment> baseAttachments) {
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

    private List<AddressJson> populateTo(List<EmailAddress> baseTo) {
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

    private List<MergeFieldJson> generateMergeFieldList(Map<String, String> mergeData) {
        List<MergeFieldJson> mergeFieldJsonList = new ArrayList<>();

        for (Map.Entry<String, String> entry : mergeData.entrySet()) {
            mergeFieldJsonList.add(new MergeFieldJson(entry.getKey(), entry.getValue()));
        }

        return mergeFieldJsonList;
    }
}
