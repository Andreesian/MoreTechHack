package ru.clickgroup.vtbmockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class VtbMockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtbMockApiApplication.class, args);
    }

}
