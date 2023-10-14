package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.atm.Atm;

import java.util.UUID;

public interface AtmEntityRepository extends JpaRepository<Atm, UUID> {
}