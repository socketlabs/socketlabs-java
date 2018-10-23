package com.socketLabs.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InjectionRequestFactory{

    private int serverId;

    private String apiKey;

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
        List<Message> messages = new ArrayList<>();
        Message message = generateBaseMessage(bulkMessage);

        message.setMergeData(populateMergeData(bulkMessage.getMergeData(), bulkMessage.getTo()));

        return mapper.writeValueAsString(message);

    }

    public String GenerateRequest(BasicMessage basicMessage) throws IOException {

        List<Message> messages = new ArrayList<>();

        Message message = generateBaseMessage(basicMessage);

        message.setTo(populateTo(basicMessage.getTo()));

        messages.add(message);

        InjectionRequest request = new InjectionRequest(this.serverId, this.apiKey, messages);
        return GetAsJson(request);
    }

    private String GetAsJson(InjectionRequest request) throws IOException {
        return mapper.writeValueAsString(request);
    }

    private Message generateBaseMessage(MessageBase messageBase) {
        Message message = new Message();

        message.setSubject(messageBase.getSubject());
        message.setPlainTextBody(messageBase.getPlainTextBody());
        message.setHtmlBody(messageBase.getHtmlBody());
        message.setMailingId(messageBase.getMailingId());
        message.setMessageId(messageBase.getMessageId());
        message.setCharset(messageBase.getCharset());
        message.setFrom(new Address(messageBase.getFrom().getEmailAddress(), messageBase.getFrom().getFriendlyName()));
        message.setCustomHeaders(populateCustomHeaders(messageBase.getCustomHeaders()));
        message.setAttachments(populateAttachments(messageBase.getAttachments()));

        if (messageBase.getApiTemplate() != null) {
            message.setApiTemplate(String.valueOf(messageBase.getApiTemplate()));
        }

        return message;
    }

    // TODO: Some of these methods are really similar so maybe we should look at refactoring

    private List<CustomHeader> populateCustomHeaders(List<com.socketLabs.models.CustomHeader> baseCustomHeaders) {
        if (baseCustomHeaders == null) {
            return null;
        }
        List<CustomHeader> customHeaders = new ArrayList<>();

        for (com.socketLabs.models.CustomHeader baseCustomHeader: baseCustomHeaders) {
            customHeaders.add(new CustomHeader(baseCustomHeader.getName(), baseCustomHeader.getValue()));
        }
        return customHeaders;
    }

    private List<Attachment> populateAttachments(List<com.socketLabs.models.Attachment> baseAttachments) {
        if (baseAttachments == null) {
            return null;
        }
        List<Attachment> attachments = new ArrayList<>();

        for (com.socketLabs.models.Attachment baseAttachment: baseAttachments) {
            Attachment attachment = new Attachment();
            attachment.setContent(baseAttachment.getContent());
            attachment.setContentId(baseAttachment.getContentId());
            attachment.setMimeType(baseAttachment.getMimeType());
            attachment.setName(baseAttachment.getName());
            attachment.setCustomHeaders(populateCustomHeaders(baseAttachment.getCustomHeaders()));
            attachments.add(attachment);
        }

        return attachments;
    }

    private List<Address> populateTo(List<EmailAddress> baseTo) {
        if (baseTo == null) {
            return null;
        }
        List<Address> addresses = new ArrayList<>();

        for (EmailAddress baseAddress: baseTo) {
            Address address = new Address(baseAddress.getEmailAddress(), baseAddress.getFriendlyName());
            addresses.add(address);
        }

        return addresses;
    }

    private MergeData populateMergeData(Map<String, String> global, List<BulkRecipient> recipients) {

        List<List<MergeField>> perMessageMergeFields = new ArrayList<>();
        List<MergeField> globalMergeFields = new ArrayList<>();

        for(BulkRecipient recipient : recipients) {
            List<MergeField> mergeFieldList = new ArrayList<>();

            for (Map.Entry<String, String> entry : recipient.getMergeData().entrySet()) {
                mergeFieldList.add(new MergeField(entry.getKey(), entry.getValue()));
            }

            mergeFieldList.add(new MergeField("DeliveryAddress",recipient.getEmailAddress()));

            if (recipient.getFriendlyName() != null) {
                mergeFieldList.add(new MergeField("FriendlyName", recipient.getFriendlyName()));
            }

            perMessageMergeFields.add(mergeFieldList);
        }

        for (Map.Entry<String, String> entry : global.entrySet()) {
            globalMergeFields.add(new MergeField(entry.getKey(), entry.getValue()));
        }

        return new MergeData(perMessageMergeFields, globalMergeFields);
    }
}

