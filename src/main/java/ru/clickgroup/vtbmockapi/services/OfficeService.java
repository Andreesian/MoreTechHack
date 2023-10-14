package ru.clickgroup.vtbmockapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clickgroup.vtbmockapi.domain.office.test_models.Office;
import ru.clickgroup.vtbmockapi.repo.OfficeRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeRepository officeRepository;


    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }
}
