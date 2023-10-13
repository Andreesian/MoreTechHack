package ru.clickgroup.vtbmockapi.domain.atm;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "atm")
@Data
@RepositoryRestResource
public class AtmEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String address;
    private double latitude;
    private double longitude;
    private boolean allDay;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "atm_services", joinColumns = @JoinColumn(name = "atm_id"))
    private List<Service> services;
}