package ru.clickgroup.vtbmockapi.domain.atm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "atm_services")
public class Service {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String serviceCapability;
    private String serviceActivity;

}