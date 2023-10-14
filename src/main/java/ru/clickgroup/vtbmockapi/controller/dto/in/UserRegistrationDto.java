package ru.clickgroup.vtbmockapi.controller.dto.in;

import lombok.Data;

@Data
public class UserRegistrationDto {

    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private String lastName;
}
