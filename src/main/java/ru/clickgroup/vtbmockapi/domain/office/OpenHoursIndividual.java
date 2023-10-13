package ru.clickgroup.vtbmockapi.domain.office;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "office_open_hours_individual")
public class OpenHoursIndividual {

    @Id
    @GeneratedValue
    private UUID id;
    private String days;
    private String hours;
}