package ru.clickgroup.vtbmockapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clickgroup.vtbmockapi.domain.atm.Atm;

import java.util.List;
@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {
    List<Atm> findAll();
}
