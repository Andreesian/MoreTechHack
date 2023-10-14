package ru.clickgroup.vtbmockapi.dto;

import java.util.List;

public class AtmWrapper {
    private List<AtmData> atmData;

    // Геттеры и сеттеры

    public List<AtmData> getAtms() {
        return atmData;
    }

    public void setAtms(List<AtmData> atmData) {
        this.atmData = atmData;
    }
}