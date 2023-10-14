package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;
import ru.clickgroup.vtbmockapi.services.AtmService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/atms")
@Tag(name = "ATM")
@RequiredArgsConstructor
public class AtmController {

    private final AtmService atmService;

    private static final int EARTH_RADIUS = 6371;

    private double calculateDistance(AtmEntity atm, Point point) {
        double lat1 = atm.getLatitude();
        double lon1 = atm.getLongitude();
        double lat2 = point.getY();
        double lon2 = point.getX();

        double dLat = toRadians(lat2 - lat1);
        double dLon = toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        //System.out.println("Distance between point and " + atm.getAddress() + " is " + distance);
        return distance;
    }

    private double toRadians(double degree) {
        return degree * Math.PI / 180;
    }
    @PostMapping("/closest")
    @Operation(summary = "Получить ближайшие банкоматы", security = @SecurityRequirement(name = "bearerAuth"))
    public List<AtmEntity> getClosestAtms(@RequestBody Point point) {
        List<AtmEntity> atms = atmService.getAllAtms();
        atms.sort(Comparator.comparingDouble(a -> calculateDistance(a, point)));
        return atms.subList(0, 1);
    }

    @GetMapping
    @Operation(summary = "Получить все банкоматы", security = @SecurityRequirement(name = "bearerAuth"))
    public List<AtmEntity> getAllAtms() {
        return atmService.getAllAtms();
    }

    @PostMapping("/import-atms")
    @Operation(summary = "Импортировать все ATM из JSON", security = @SecurityRequirement(name = "bearerAuth"))
    public void importATMs(@RequestBody List<AtmEntity> atms) {
        atmService.importAllAtms(atms);
    }
}