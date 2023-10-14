package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.clickgroup.vtbmockapi.controller.dto.falcon.QueryPointRequest;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;
import ru.clickgroup.vtbmockapi.services.FalconService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag( name = "GPT_QUERY")
public class QueryController {
    @Autowired
    private FalconService falconService;

    @PostMapping("/query")
    @Operation(summary = "Запромтить вопрос в gpt систему", security = @SecurityRequirement(name = "bearerAuth"))
    public String myEndpoint(@RequestBody QueryPointRequest request) {
        Query query = request.getQuery();
        Point point = request.getPoint();
        String result = falconService.getActionAndCategory(query, point);
        return result;
    }
}
