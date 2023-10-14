package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHours;

import java.util.UUID;

public interface OfficeOpenHoursRepository extends JpaRepository<OfficeOpenHours, UUID> {
}
