package ru.clickgroup.vtbmockapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.domain.atm.Atm;
import ru.clickgroup.vtbmockapi.domain.atm.AtmService;
import ru.clickgroup.vtbmockapi.dto.AtmData;
import ru.clickgroup.vtbmockapi.dto.AtmEntityData;
import ru.clickgroup.vtbmockapi.dto.AtmServiceData;
import ru.clickgroup.vtbmockapi.repo.AtmEntityRepository;
import ru.clickgroup.vtbmockapi.repo.AtmRepository;
import ru.clickgroup.vtbmockapi.repo.AtmServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/atms")
public class AtmProvideController {

    @Autowired
    private AtmRepository atmRepository;
    @Autowired
    private AtmServiceRepository atmServiceRepository;

    @PostMapping("/import")
    public ResponseEntity<String> importAtms(@RequestBody String atmData) {
        System.out.println(atmData.getAtmEntityData());
        AtmEntityData atmEntityData = atmData.getAtmEntityData();
        Atm atm = new Atm();
        atm.setAllDay(atmEntityData.isAllDay());
        atm.setLatitude(atmEntityData.getLatitude());
        atm.setLongitude(atmEntityData.getLongitude());
        atm.setAddress(atmEntityData.getAddress());

        atmRepository.save(atm);

        Map<String, AtmServiceData> services = atmEntityData.getServices();
        for (Map.Entry<String, AtmServiceData> entry : services.entrySet()) {
            AtmServiceData serviceData = entry.getValue();
            AtmService atmService = new AtmService();
            atmService.setName(entry.getKey());
            atmService.setServiceCapability(serviceData.getServiceCapability());
            atmService.setServiceActivity(serviceData.getServiceActivity());
            atmService.setAtm(atm);
            atmServiceRepository.save(atmService);
        }

        return ResponseEntity.ok("Данные успешно импортированы в базу данных.");
    }
}