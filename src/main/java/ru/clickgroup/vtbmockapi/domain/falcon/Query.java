package ru.clickgroup.vtbmockapi.domain.falcon;

import lombok.Data;

@Data
public class Query {
    private String text;

    public Query(String value) {
        this.text = value;
    }

    public String getValue() {
        return text;
    }
}
