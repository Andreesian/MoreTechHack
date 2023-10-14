package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clickgroup.vtbmockapi.domain.office.OfficeEntity;

import java.util.List;
@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {
    List<OfficeEntity> findAll();
}
