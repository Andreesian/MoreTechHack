package ru.clickgroup.vtbmockapi.controller.dto.out;

import lombok.Data;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;

@Data
public class AuthSuccessOut {
    private String token;
    private Long expiresAt;
    private Long issueDate;
    private UserEntity user;
}
