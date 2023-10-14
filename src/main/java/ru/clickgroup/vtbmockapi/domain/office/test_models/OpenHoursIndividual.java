package ru.clickgroup.vtbmockapi.domain.office.test_models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class OpenHoursIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String days;
    private String hours;

    // Геттеры и сеттеры


    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}