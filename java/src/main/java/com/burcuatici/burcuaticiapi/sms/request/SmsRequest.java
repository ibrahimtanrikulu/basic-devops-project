package com.burcuatici.burcuaticiapi.sms.request;

import java.time.LocalDateTime;
import java.util.List;

public class SmsRequest {

    private String apiId;
    private String apiKey;
    private String sender;
    private LocalDateTime randevuTarih;
    private String messageType;
    private String message;
    private String messageContentType;
    private List<String> phones;

    public SmsRequest(String apiId, String apiKey, String sender, LocalDateTime randevuTarih,String messageType, String message, String messageContentType, List<String> phones) {
        this.apiId = apiId;
        this.apiKey = apiKey;
        this.sender = sender;
        this.randevuTarih = randevuTarih;
        this.messageType = messageType;
        this.message = message;
        this.messageContentType = messageContentType;
        this.phones = phones;
    }

    public SmsRequest() {

    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getRandevuTarih() {
        return randevuTarih;
    }

    public void setRandevuTarih(LocalDateTime randevuTarih) {
        this.randevuTarih = randevuTarih;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageContentType() {
        return messageContentType;
    }

    public void setMessageContentType(String messageContentType) {
        this.messageContentType = messageContentType;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}

