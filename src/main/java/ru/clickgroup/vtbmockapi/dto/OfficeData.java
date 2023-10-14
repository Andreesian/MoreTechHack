package ru.clickgroup.vtbmockapi.dto;

import java.util.List;

public class OfficeData {
    private String salePointName;
    private String address;
    private String status;
    private List<OpenHoursData> openHours;
    private String rko;
    private List<OpenHoursData> openHoursIndividual;
    private String officeType;
    private String salePointFormat;
    private String suoAvailability;
    private String hasRamp;
    private double latitude;
    private double longitude;
    private String metroStation;
    private int distance;
    private boolean kep;
    private boolean myBranch;

    // Геттеры и сеттеры

    public String getSalePointName() {
        return salePointName;
    }

    public void setSalePointName(String salePointName) {
        this.salePointName = salePointName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OpenHoursData> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<OpenHoursData> openHours) {
        this.openHours = openHours;
    }

    public String getRko() {
        return rko;
    }

    public void setRko(String rko) {
        this.rko = rko;
    }

    public List<OpenHoursData> getOpenHoursIndividual() {
        return openHoursIndividual;
    }

    public void setOpenHoursIndividual(List<OpenHoursData> openHoursIndividual) {
        this.openHoursIndividual = openHoursIndividual;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getSalePointFormat() {
        return salePointFormat;
    }

    public void setSalePointFormat(String salePointFormat) {
        this.salePointFormat = salePointFormat;
    }

    public String getSuoAvailability() {
        return suoAvailability;
    }

    public void setSuoAvailability(String suoAvailability) {
        this.suoAvailability = suoAvailability;
    }

    public String getHasRamp() {
        return hasRamp;
    }

    public void setHasRamp(String hasRamp) {
        this.hasRamp = hasRamp;
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

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isKep() {
        return kep;
    }

    public void setKep(boolean kep) {
        this.kep = kep;
    }

    public boolean isMyBranch() {
        return myBranch;
    }

    public void setMyBranch(boolean myBranch) {
        this.myBranch = myBranch;
    }
}

