package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;

import java.util.List;

public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {
    List<OfficeEntity> findAll();
}
