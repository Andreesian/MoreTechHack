package ru.clickgroup.vtbmockapi.domain.office;

import jakarta.persistence.*;
import ru.clickgroup.vtbmockapi.domain.servicecategory.Category;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "office")
public class OfficeEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String salePointName;
    private String address;
    private String status;
    private String rko;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "office_categories", joinColumns = @JoinColumn(name = "office_id"))
    private List<Category> categorys;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "office_open_hours", joinColumns = @JoinColumn(name = "office_id"))
    private List<OpenHoursEntity> openHours;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "office_open_hours_individual", joinColumns = @JoinColumn(name = "office_id"))
    private List<OpenHoursIndividual> openHoursIndividual;

    public OfficeEntity() {
    }
    
    // Геттеры и сеттеры (если необходимы)
}





