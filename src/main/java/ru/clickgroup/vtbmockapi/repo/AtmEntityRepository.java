package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;

import java.util.UUID;

public interface AtmEntityRepository extends JpaRepository<AtmEntity, UUID> {
}