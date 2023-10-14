package ru.clickgroup.vtbmockapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.clickgroup.vtbmockapi.controller.AtmController;
import ru.clickgroup.vtbmockapi.controller.OfficeProvideController;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;
import ru.clickgroup.vtbmockapi.domain.falcon.Response;

@Service
@RequiredArgsConstructor
public class FalconService {

    @Value("${services.falcon.url}")
    private String falconEndpointUrl;

    @Autowired
    AtmController atmController;

    @Autowired
    OfficeProvideController officeProvideController;

    static String responseTemplate = "answer in the format of json depending on what user wants to do as following template:\n" +
            "{\n" +
            "\"action\":\"\",\n" +
            "\"category\":\"\"\n" +
            "}\n" +
            "action can be only \"find_atm\", \"find_office\" or \"frequently_visited_places\". always default to those closest actions\n" +
            "category can be only none, blind or qrRead or wheelchair. always default to none.\n";
    private final RestTemplate restTemplate;
    public String getActionAndCategory(Query data, Point point) {
        data.setText(FalconService.responseTemplate + ' ' + data.getText());
        //System.out.println(data);
        HttpEntity<Query> entity = new HttpEntity<>(data);
        ResponseEntity<Response> response = restTemplate.postForEntity(falconEndpointUrl + "/ask", entity, Response.class);
        //System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + response.getBody().getResult());
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                System.out.println(response.getBody().getResult());
                JsonNode rootNode = objectMapper.readTree(response.getBody().getResult());
                JsonNode activeNode = rootNode.get("action");
                if (activeNode != null && activeNode.isTextual()) {
                    String activeValue = activeNode.asText();
                    //System.out.println(activeValue);
                    switch (activeValue) {
                        case "find_atm":
                            System.out.println();
                            System.out.println("Выполняется find_atm.");
                            return atmController.getClosestAtms(point).toString();
                        case "find_office":
                            // Обработка других значений "active"
                            System.out.println("Выполняется find_office.");
                            return officeProvideController.getClosestOffices(point).toString();
                        case "frequently_visited_places":
                            System.out.println("Выполняется frequently_visited_places.");
                            break;
                    }
                } else {
                    System.out.println("Команда не найдена!");
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + response.getBody().getResult());
            return response.getBody().getResult();
        } else {
            throw new RuntimeException("Failed to send data: " + response.getStatusCode());
        }
    }

    public String sendPostRequest(Query data) {
        HttpEntity<Query> entity = new HttpEntity<>(data);
        ResponseEntity<Response> response = restTemplate.postForEntity(falconEndpointUrl+ "/ask", entity, Response.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResult();
        } else {
            throw new RuntimeException("Failed to send data: " + response.getStatusCode());
        }
    }
}