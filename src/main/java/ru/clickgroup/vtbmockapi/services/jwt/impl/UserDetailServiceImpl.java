package ru.clickgroup.vtbmockapi.services.jwt.impl;

import com.fullstatestudio.macgicman.entity.UserEntity;
import com.fullstatestudio.macgicman.repository.UserEntityRepository;
import com.fullstatestudio.macgicman.service.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userLogin) {
        log.debug("Loading user with login: " + userLogin);
        UserEntity user = userRepository.findUserEntityByLogin(userLogin).orElseThrow();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}