package ru.clickgroup.vtbmockapi.domain.office.test_models;

import jakarta.persistence.*;
import ru.clickgroup.vtbmockapi.domain.office.test_models.Office;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OpenHours;

import java.util.UUID;

@Entity
public class OfficeOpenHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne
    @JoinColumn(name = "open_hours_id")
    private OpenHours openHours;

    private String days;
    private String hours;

    // Геттеры и сеттеры


    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public OpenHours getOpenHours() {
        return openHours;
    }

    public void setOpenHours(OpenHours openHours) {
        this.openHours = openHours;
    }

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
