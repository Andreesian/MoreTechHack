package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clickgroup.vtbmockapi.domain.office.test_models.OpenHoursIndividual;

import java.util.UUID;

public interface OpenHoursIndividualRepository extends JpaRepository<OpenHoursIndividual, UUID> {
}
