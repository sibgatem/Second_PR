package com.example.Second_PR.repo;

import com.example.Second_PR.models.Breed;
import com.example.Second_PR.models.Depart;
import org.springframework.data.repository.CrudRepository;

public interface DepartRepository extends CrudRepository <Depart, Long> {
    Depart findByName(String name);
}
