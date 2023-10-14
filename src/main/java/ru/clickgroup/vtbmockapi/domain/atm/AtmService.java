package ru.clickgroup.vtbmockapi.domain.atm;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
public class AtmService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String serviceCapability;
    private String serviceActivity;

    @ManyToOne
    @JoinColumn(name = "atm_id")
    private Atm atm;

    // Геттеры и сеттеры


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }
}