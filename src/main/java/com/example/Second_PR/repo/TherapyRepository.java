package com.example.Second_PR.repo;

import com.example.Second_PR.models.Therapy;
import org.springframework.data.repository.CrudRepository;

public interface TherapyRepository extends CrudRepository<Therapy, Long> {
    Therapy findByName(String name);
}
