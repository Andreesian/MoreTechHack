package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.atm.AtmService;

import java.util.UUID;

public interface AtmServiceRepository extends JpaRepository<AtmService, UUID> {
}