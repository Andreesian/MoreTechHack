package ru.clickgroup.vtbmockapi.repo;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.test_models.*;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OpenHoursIndividual;

//public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {
//    List<OfficeEntity> findAll();
//}

public interface OfficeRepository extends JpaRepository<Office, UUID> {
}

