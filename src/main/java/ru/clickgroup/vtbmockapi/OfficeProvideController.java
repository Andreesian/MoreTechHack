package ru.clickgroup.vtbmockapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;
import ru.clickgroup.vtbmockapi.repo.OfficeRepository;

import java.util.List;

@RestController
@RequestMapping("offices")
public class OfficeProvideController {

    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping
    public List<OfficeEntity> getAllOffices() {return officeRepository.findAll();
    }
}