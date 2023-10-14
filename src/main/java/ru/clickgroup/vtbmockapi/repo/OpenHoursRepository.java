package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OpenHours;

import java.util.UUID;

public interface OpenHoursRepository extends JpaRepository<OpenHours, UUID> {
}
