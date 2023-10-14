package ru.clickgroup.vtbmockapi.dto;

public class AtmServiceData {
    private String serviceCapability;
    private String serviceActivity;
    // Геттеры и сеттеры


    public String getServiceCapability() {
        return serviceCapability;
    }

    public void setServiceCapability(String serviceCapability) {
        this.serviceCapability = serviceCapability;
    }

    public String getServiceActivity() {
        return serviceActivity;
    }

    public void setServiceActivity(String serviceActivity) {
        this.serviceActivity = serviceActivity;
    }
}