package ru.clickgroup.vtbmockapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}