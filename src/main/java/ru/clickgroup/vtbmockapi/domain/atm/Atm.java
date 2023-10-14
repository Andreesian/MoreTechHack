package ru.clickgroup.vtbmockapi.domain.atm;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@Entity
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private boolean allDay;
    private double latitude;
    private double longitude;
    private String address;

    @OneToMany(mappedBy = "atm", cascade = CascadeType.ALL)
    private List<AtmService> services;

    // Геттеры и сеттеры


    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AtmService> getServices() {
        return services;
    }

    public void setServices(List<AtmService> services) {
        this.services = services;
    }
}