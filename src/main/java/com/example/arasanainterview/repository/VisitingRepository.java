package com.example.arasanainterview.repository;

import com.example.arasanainterview.domain.Visit;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VisitingRepository extends PagingAndSortingRepository<Visit, Long> {
    List<Visit> findAllBy();
}
