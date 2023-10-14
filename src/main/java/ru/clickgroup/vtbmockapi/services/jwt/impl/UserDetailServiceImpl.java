package ru.clickgroup.vtbmockapi.services.jwt.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;
import ru.clickgroup.vtbmockapi.repo.UserEntityRepository;
import ru.clickgroup.vtbmockapi.services.jwt.CustomUserDetails;

@Service
@RequiredArgsConstructor
@Log4j2
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userLogin) {
        log.debug("Loading user with login: " + userLogin);
        UserEntity user = userRepository.findUserEntityByEmail(userLogin).orElseThrow();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}