package ru.clickgroup.vtbmockapi.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;
import ru.clickgroup.vtbmockapi.repo.AtmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtmService {

    private final AtmRepository atmRepository;


    public List<AtmEntity> getAllAtms() {
        return atmRepository.findAll();
    }

    public void importAllAtms(List<AtmEntity> atms) {
        atmRepository.saveAll(atms);
    }
}
