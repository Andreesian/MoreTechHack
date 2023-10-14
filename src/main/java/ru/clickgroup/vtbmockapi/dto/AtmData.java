package ru.clickgroup.vtbmockapi.dto;

import ru.clickgroup.vtbmockapi.domain.atm.AtmService;

import java.util.Map;

public class AtmData {
    private AtmEntityData atmEntityData;

    public AtmEntityData getAtmEntityData() {
        return atmEntityData;
    }

    public void setAtmEntityData(AtmEntityData atmEntityData) {
        this.atmEntityData = atmEntityData;
    }
}