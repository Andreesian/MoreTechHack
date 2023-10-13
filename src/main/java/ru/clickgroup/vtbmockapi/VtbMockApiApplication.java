package ru.clickgroup.vtbmockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VtbMockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtbMockApiApplication.class, args);
    }

}
