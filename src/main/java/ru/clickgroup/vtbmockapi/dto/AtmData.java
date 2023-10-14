package ru.clickgroup.vtbmockapi.dto;

import java.util.Map;

public class AtmData {
    private String address;
    private double latitude;
    private double longitude;
    private boolean allDay;
    private Map<String, AtmServiceData> services;

    // Геттеры и сеттеры


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public Map<String, AtmServiceData> getServices() {
        return services;
    }

    public void setServices(Map<String, AtmServiceData> services) {
        this.services = services;
    }
}