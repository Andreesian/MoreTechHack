package ru.clickgroup.vtbmockapi.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clickgroup.vtbmockapi.controller.dto.in.AuthDtoEmail;
import ru.clickgroup.vtbmockapi.controller.dto.in.AuthDtoPhone;
import ru.clickgroup.vtbmockapi.controller.dto.out.AuthSuccessOut;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;
import ru.clickgroup.vtbmockapi.repo.UserEntityRepository;
import ru.clickgroup.vtbmockapi.services.jwt.CustomUserDetails;
import ru.clickgroup.vtbmockapi.services.jwt.JwtProvider;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @SneakyThrows
    public AuthSuccessOut authByEmail(AuthDtoEmail authDtoEmail) {
        UserEntity user = userEntityRepository.findUserEntityByEmail(authDtoEmail.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            if (passwordEncoder.matches(authDtoEmail.getPassword(), user.getPassword())) {
                //if password is good build the response with token
                return createResponseByUser(user);
            } else throw new Exception("Authentication credentials not good for: " + authDtoEmail.getEmail());
        } else throw new Exception("Authentication fails for: " + authDtoEmail.getEmail());
    }

    private AuthSuccessOut createResponseByUser(UserEntity user) {
        AuthSuccessOut authSuccessOut = new AuthSuccessOut();
        authSuccessOut.setUser(user);
        authSuccessOut.setToken(jwtProvider.generateToken(user));
        //TODO: set issuedAt and expiresAt

        return  authSuccessOut;
    }

    @SneakyThrows
    public AuthSuccessOut authByPhone(AuthDtoPhone authDtoPhone) {
        UserEntity user = userEntityRepository.findUserEntityByPhoneNumber(authDtoPhone.getPhoneNumber()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            if (passwordEncoder.matches(authDtoPhone.getPassword(), user.getPassword())) {
                //if password is good build the response with token
                return createResponseByUser(user);
            } else throw new Exception("Authentication credentials not good for: " + authDtoPhone.getPhoneNumber());
        } else throw new Exception("Authentication fails for: " + authDtoPhone.getPhoneNumber());
    }

    @SneakyThrows
    public UserEntity getCurrentUserByToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new Exception("Get error while retrieving current user authentication. The current SecurityContext is null");
        }
        Object principal = auth.getPrincipal();
        UserEntity user;
        if (principal instanceof CustomUserDetails) {
            user = ((CustomUserDetails) principal).getUser();
        } else {
            throw new Exception("No user found in security context");
        }
        log.debug("Current user is: " + user.getLogin());
        return user;
    }
}
