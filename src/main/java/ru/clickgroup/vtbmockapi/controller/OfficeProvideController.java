package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;
import ru.clickgroup.vtbmockapi.domain.office.test_models.Office;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHours;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHoursIndividual;
import ru.clickgroup.vtbmockapi.dto.OfficeData;
import ru.clickgroup.vtbmockapi.dto.OpenHoursData;
import ru.clickgroup.vtbmockapi.repo.*;
import ru.clickgroup.vtbmockapi.repo.OfficeRepository;

import java.util.List;

@RestController
@RequestMapping("/offices")
@Tag( name = "OFFICE")
public class OfficeProvideController {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeOpenHoursRepository officeOpenHoursRepository;

    @Autowired
    private OfficeCategoryRepository officeCategoryRepository;

    @Autowired
    private OfficeOpenHoursIndividualRepository officeOpenHoursIndividualRepository;

    //    @GetMapping
//    public List<OfficeEntity> getAllOffices() {
//        return OfficeRepository.findAll();
//    }
//

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