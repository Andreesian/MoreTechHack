package ru.clickgroup.vtbmockapi.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;
import ru.clickgroup.vtbmockapi.repo.AtmRepository;

import java.util.List;

@RestController
@RequestMapping("/atms")
public class AtmProvideController {

    @Autowired
    private AtmRepository atmRepository;

    @GetMapping
    public List<AtmEntity> getAllAtms() {
        return atmRepository.findAll();
    }

    @PostMapping("/import-atms")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public String importATMs(@RequestBody String jsonData) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject data = (JsonObject) parser.parse(jsonData);
            JsonArray atms = data.getAsJsonArray("atms");

            for (int i = 0; i < atms.size(); i++) {
                JsonObject atmData = atms.get(i).getAsJsonObject();
                AtmEntity atm = new AtmEntity();
                atm.setAddress(atmData.get("address").getAsString());
                atm.setLatitude(atmData.get("latitude").getAsDouble());
                atm.setLongitude(atmData.get("longitude").getAsDouble());
                atm.setAllDay(atmData.get("allDay").getAsBoolean());
                atmRepository.save(atm);
            }

            return "Данные успешно импортированы в базу данных.";
        } catch (Exception e) {
            return "Ошибка при импорте данных: " + e.getMessage();
        }
    }
}