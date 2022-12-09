package com.example.arasanainterview.repository;

import com.example.arasanainterview.domain.Examination;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExaminationRepository extends PagingAndSortingRepository<Examination,Long> {
    List<Examination> findAllBy();
}
