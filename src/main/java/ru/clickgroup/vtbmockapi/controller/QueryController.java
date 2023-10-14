package ru.clickgroup.vtbmockapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;
import ru.clickgroup.vtbmockapi.services.FalconService;

@RestController
@RequiredArgsConstructor
public class QueryController {
    @Autowired
    private FalconService falconService;

    @PostMapping("/query")
    public String myEndpoint(@RequestBody Query query) {
        String result = falconService.getActionAndCategory(query);
        return result;
    }
}
