package ru.clickgroup.vtbmockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public ResponseEntity<String> importAtms(@RequestBody String jsonData) {
        try {
            // Парсим JSON
            JsonParser parser = new JsonParser();
            JsonObject data = (JsonObject) parser.parse(jsonData);
            JsonArray atms = data.getAsJsonArray("atms");

            // Перебираем элементы массива "atms"
            for (int i = 0; i < atms.size(); i++) {
                JsonObject atmData = atms.get(i).getAsJsonObject();
                JsonObject atmEntityData = atmData.getAsJsonObject("atmEntityData");

                // Создаем и сохраняем объект Atm
                Atm atm = new Atm();
                atm.setAllDay(atmEntityData.get("allDay").getAsBoolean());
                atm.setLatitude(atmEntityData.get("latitude").getAsDouble());
                atm.setLongitude(atmEntityData.get("longitude").getAsDouble());
                atm.setAddress(atmEntityData.get("address").getAsString());

                atmRepository.save(atm);

                // Перебираем и сохраняем сервисы
                JsonObject services = atmEntityData.getAsJsonObject("services");
                for (Map.Entry<String, JsonElement> entry : services.entrySet()) {
                    JsonObject serviceData = entry.getValue().getAsJsonObject();
                    AtmService atmService = new AtmService();
                    atmService.setName(entry.getKey());
                    atmService.setServiceCapability(serviceData.get("serviceCapability").getAsString());
                    atmService.setServiceActivity(serviceData.get("serviceActivity").getAsString());
                    atmService.setAtm(atm);
                    atmServiceRepository.save(atmService);
                }
            }

            return ResponseEntity.ok("Данные успешно импортированы в базу данных.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка при обработке JSON: " + e.getMessage());
        }
    }
}