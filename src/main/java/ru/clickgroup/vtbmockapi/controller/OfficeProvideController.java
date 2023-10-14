package ru.clickgroup.vtbmockapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;
import ru.clickgroup.vtbmockapi.repo.OfficeRepository;

import java.util.List;

@RestController
@RequestMapping("/offices")
public class OfficeProvideController {

    private OfficeRepository OfficeRepository;

    @GetMapping
    public List<OfficeEntity> getAllOffices() {
        return OfficeRepository.findAll();
    }
}