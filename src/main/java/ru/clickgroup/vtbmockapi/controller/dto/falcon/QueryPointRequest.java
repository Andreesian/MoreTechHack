package ru.clickgroup.vtbmockapi.controller.dto.falcon;

import lombok.Data;
import org.springframework.data.geo.Point;
import ru.clickgroup.vtbmockapi.domain.falcon.Query;

@Data
public class QueryPointRequest {
    private Query query;
    private Point point;

}
