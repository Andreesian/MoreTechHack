package ru.clickgroup.vtbmockapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clickgroup.vtbmockapi.controller.dto.in.AuthDtoEmail;
import ru.clickgroup.vtbmockapi.controller.dto.in.AuthDtoPhone;
import ru.clickgroup.vtbmockapi.controller.dto.in.UserRegistrationDto;
import ru.clickgroup.vtbmockapi.controller.dto.out.AuthSuccessOut;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;
import ru.clickgroup.vtbmockapi.services.AuthService;

@RestController
@Tag( name = "AUTH")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/auth/email")
    AuthSuccessOut authUserByEmail(@RequestBody  AuthDtoEmail authDtoEmail){
        return authService.authByEmail(authDtoEmail);
    }

    @PostMapping("/auth/phone")
    AuthSuccessOut authUserByPhone(@RequestBody AuthDtoPhone authDtoPhone){
        return authService.authByPhone(authDtoPhone);
    }

    @GetMapping("/auth/currentUser")
    @Operation(summary = "Получить текущего юзера по аутентификации", security = @SecurityRequirement(name = "bearerAuth"))
    UserEntity getCurrentUserByToken(){
        return authService.getCurrentUserByToken();
    }

    @PostMapping("/auth/new_user")
    @Operation(summary = "Подать заявку на регистрацию нового пользователя", security = @SecurityRequirement(name = "bearerAuth"))
    AuthSuccessOut authNewUser(@RequestBody UserRegistrationDto urd){
        return authService.authNewUser(urd);
    }
}
