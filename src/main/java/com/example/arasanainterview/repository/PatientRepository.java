package com.example.arasanainterview.repository;

import com.example.arasanainterview.domain.PatientUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PatientRepository extends PagingAndSortingRepository<PatientUser, Long> {
    List<PatientUser> findAll();
}
