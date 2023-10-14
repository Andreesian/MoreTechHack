package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;
import ru.clickgroup.vtbmockapi.services.FalconService;

@RestController
@RequiredArgsConstructor
@Tag( name = "GPT_QUERY")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QueryController {
    @Autowired
    private FalconService falconService;

    @PostMapping("/query")
    @Operation(summary = "Запромтить вопрос в gpt систему", security = @SecurityRequirement(name = "bearerAuth"))
    public String myEndpoint(@RequestBody Query query) {
        String result = falconService.getActionAndCategory(query);
        return result;
    }
}
