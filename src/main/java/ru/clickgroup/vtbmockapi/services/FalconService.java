package ru.clickgroup.vtbmockapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;
import ru.clickgroup.vtbmockapi.domain.falcon.Response;

@Service
@RequiredArgsConstructor
public class FalconService {

    static String responseTemplate = "answer in the format of json depending on what user wants to do as following template:\n" +
            "{\n" +
            "\"action\":\"\",\n" +
            "\"category\":\"\"\n" +
            "}\n" +
            "action can be only \"find_atm\", \"find_office\" or \"frequently_visited_places\". always default to those closest actions\n" +
            "category can be only none, blind or qrRead or wheelchair";
    private final RestTemplate restTemplate;
    public String getActionAndCategory(Query data) {
        data.setText(FalconService.responseTemplate + data.getText());
        HttpEntity<Query> entity = new HttpEntity<>(data);
        ResponseEntity<Response> response = restTemplate.postForEntity("http://localhost:5000/ask", entity, Response.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResult();
        } else {
            throw new RuntimeException("Failed to send data: " + response.getStatusCode());
        }
    }

    public String sendPostRequest(Query data) {
        HttpEntity<Query> entity = new HttpEntity<>(data);
        ResponseEntity<Response> response = restTemplate.postForEntity("http://localhost:5000/ask", entity, Response.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResult();
        } else {
            throw new RuntimeException("Failed to send data: " + response.getStatusCode());
        }
    }
}