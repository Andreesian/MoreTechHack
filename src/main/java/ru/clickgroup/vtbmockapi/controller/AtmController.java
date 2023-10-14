package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;
import ru.clickgroup.vtbmockapi.services.AtmService;

import java.util.List;

@RestController
@RequestMapping("/atms")
@Tag(name = "ATM")
@RequiredArgsConstructor
public class AtmController {

    private final AtmService atmService;


    @GetMapping
    public List<AtmEntity> getAllAtms() {
        return atmService.getAllAtms();
    }

    @PostMapping("/import-atms")
    @Operation(summary = "Импортировать все ATM из JSON", security = @SecurityRequirement(name = "bearerAuth"))
    public void importATMs(@RequestBody List<AtmEntity> atms) {
        atmService.importAllAtms(atms);
    }
}