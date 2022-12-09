package com.example.arasanainterview.repository;

import com.example.arasanainterview.domain.PatientUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends PagingAndSortingRepository<PatientUser, Long> {
    List<PatientUser> findAll();

    Optional<PatientUser> findById(@Param("id") long id);

    boolean existsByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    Optional<PatientUser> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
