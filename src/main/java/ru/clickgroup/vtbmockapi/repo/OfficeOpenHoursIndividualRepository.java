package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OfficeOpenHoursIndividual;

import java.util.UUID;

public interface OfficeOpenHoursIndividualRepository extends JpaRepository<OfficeOpenHoursIndividual, UUID> {
}
