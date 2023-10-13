package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.atm.AtmEntity;

import java.util.List;

public interface AtmRepository extends JpaRepository<AtmEntity, Long> {
    List<AtmEntity> findAll();
}
