package com.socketLabs.serialization;

import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.BulkMessage;
import com.socketLabs.models.MessageBase;

import java.util.ArrayList;
import java.util.List;

public class InjectionRequestFactory{

    private int serverId;

    private String apiKey;

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

    public InjectionRequest GenerateRequest(BulkMessage message) {

        // TODO: Convert Bulk to Json
        return null;

    }

    public InjectionRequest GenerateRequest(BasicMessage basicMessage) {

        List<Message> messages = new ArrayList<>();

        Message message = generateBaseMessage(basicMessage);

        messages.add(message);


        return new InjectionRequest(this.serverId, this.apiKey, messages);
    }


    // TODO: Helper methods

    private Message generateBaseMessage(MessageBase messageBase) {
        Message message = new Message();

        message.setSubject(messageBase.getSubject());
        message.setPlainTextBody(messageBase.getPlainTextBody());
        message.setHtmlBody(messageBase.getHtmlBody());
        message.setMailingId(messageBase.getMailingId());
        message.setMessageId(messageBase.getMessageId());
        message.setCharset(messageBase.getCharset());
        message.setFrom(new Address(messageBase.getFrom().getEmail(), messageBase.getFrom().getFriendlyName()));
        message.setCustomHeaders(populateCustomHeaders(messageBase.getCustomHeaders()));
        message.setAttachments(populateAttachments(messageBase.getAttachments()));

        return message;
    }

    private List<CustomHeader> populateCustomHeaders(List<com.socketLabs.models.CustomHeader> baseCustomheaders) {
        if (baseCustomheaders == null) {
            return null;
        }
        List<CustomHeader> customHeaders = new ArrayList<>();

        for (com.socketLabs.models.CustomHeader baseCustomHeader: baseCustomheaders) {
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
}

