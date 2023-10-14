package ru.clickgroup.vtbmockapi.repo;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clickgroup.vtbmockapi.domain.user.UserEntity;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);

    Optional<UserEntity> findUserEntityByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findUserEntityByEmailIgnoreCase(String email);
}
