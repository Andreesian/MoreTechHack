package ru.clickgroup.vtbmockapi.domain.office.test_models;

import jakarta.persistence.*;
import ru.clickgroup.vtbmockapi.domain.office.test_models.Office;

import java.util.UUID;

@Entity
public class OfficeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID categorysId;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    // Геттеры и сеттеры


    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
