package ru.clickgroup.vtbmockapi.domain.atm;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "atm")
@Data
public class AtmEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String address;
    private double latitude;
    private double longitude;
    private boolean allDay;

    @ElementCollection
    @CollectionTable(name = "atm_services", joinColumns = @JoinColumn(name = "atm_id"))
    @MapKeyColumn(name = "service_name")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyClass(ServiceEnum.class)
    private Map<ServiceEnum, ServiceDetails> services;

    public void addService(ServiceEnum serviceEnum, ServiceDetails details) {
        if (services == null) {
            services = new HashMap<>();
        }
        services.put(serviceEnum, details);
    }

    public enum ServiceEnum {
        wheelchair,
        blind,
        nfcForBankCards,
        qrRead,
        supportsUsd,
        supportsChargeRub,
        supportsEur,
        supportsRub
    }

    @Embeddable
    public static class ServiceDetails {
        private YourServiceCapability serviceCapability;
        private YourServiceActivity serviceActivity;

    }

    public enum YourServiceCapability {
        UNKNOWN,
        SUPPORTED,
        UNSUPPORTED
    }

    public enum YourServiceActivity {
        UNKNOWN,
        AVAILABLE,
        UNAVAILABLE
    }

}