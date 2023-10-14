package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
//import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;
import ru.clickgroup.vtbmockapi.domain.office.test_models.Office;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHours;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHoursIndividual;
import ru.clickgroup.vtbmockapi.dto.OfficeData;
import ru.clickgroup.vtbmockapi.dto.OpenHoursData;
import ru.clickgroup.vtbmockapi.repo.*;
import ru.clickgroup.vtbmockapi.repo.OfficeRepository;
import ru.clickgroup.vtbmockapi.services.OfficeService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/offices")
@Tag( name = "OFFICE")
@RequiredArgsConstructor
public class OfficeProvideController {

    private OfficeRepository officeRepository;

    private OfficeOpenHoursRepository officeOpenHoursRepository;

    private OfficeCategoryRepository officeCategoryRepository;

    private OfficeOpenHoursIndividualRepository officeOpenHoursIndividualRepository;

    private final OfficeService officeService;

    //    @GetMapping
//    public List<OfficeEntity> getAllOffices() {
//        return OfficeRepository.findAll();
//    }
//
    private static final int EARTH_RADIUS = 6371;

    private double calculateDistance(Office office, Point point) {
        double lat1 = office.getLatitude();
        double lon1 = office.getLongitude();
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
    @Operation(summary = "Получить ближайшие оффисы", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Office> getClosestOffices(@RequestBody Point point) {
        List<Office> offices = officeService.getAllOffices();
        offices.sort(Comparator.comparingDouble(a -> calculateDistance(a, point)));
        return offices.subList(0, 10);
    }

    @GetMapping("/")
    @Operation(summary = "Получить все офисы", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @PostMapping("/import")
    @Operation(summary = "Импортировать все офисы из JSON", security = @SecurityRequirement(name = "bearerAuth"))
    public String importOffices(@RequestBody List<OfficeData> officeDataList) {
        for (OfficeData data : officeDataList) {
            Office office = new Office();
            office.setSalePointName(data.getSalePointName());
            office.setAddress(data.getAddress());
            office.setStatus(data.getStatus());
            office.setOfficeType(data.getOfficeType());
            office.setSalePointFormat(data.getSalePointFormat());
            office.setSuoAvailability(data.getSuoAvailability());
            office.setHasRamp(data.getHasRamp());
            office.setLatitude(data.getLatitude());
            office.setLongitude(data.getLongitude());
            office.setMetroStation(data.getMetroStation());
            office.setDistance(data.getDistance());
            office.setKep(data.isKep());
            office.setMyBranch(data.isMyBranch());
            office.setRko(data.getRko());

            // Сохраните основную информацию об офисе
            officeRepository.save(office);

            // Обработка часов работы
            List<OpenHoursData> openHoursData = data.getOpenHours();
            for (OpenHoursData openHours : openHoursData) {
                OfficeOpenHours officeOpenHours = new OfficeOpenHours();
                officeOpenHours.setOffice(office);
                officeOpenHours.setDays(openHours.getDays());
                officeOpenHours.setHours(openHours.getHours());
                officeOpenHoursRepository.save(officeOpenHours);
            }

            // Обработка индивидуальных часов работы
            List<OpenHoursData> openHoursIndividualData = data.getOpenHoursIndividual();
            for (OpenHoursData openHours : openHoursIndividualData) {
                OfficeOpenHoursIndividual officeOpenHoursIndividual = new OfficeOpenHoursIndividual();
                officeOpenHoursIndividual.setOffice(office);
                officeOpenHoursIndividual.setDays(openHours.getDays());
                officeOpenHoursIndividual.setHours(openHours.getHours());
                officeOpenHoursIndividualRepository.save(officeOpenHoursIndividual);
            }
        }

        return "Данные успешно импортированы в базу данных.";
    }

}