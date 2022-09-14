package com.example.Second_PR.repo;

import com.example.Second_PR.models.Breed;
import com.example.Second_PR.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepository extends CrudRepository<Breed, Long> {
        Breed findByName(String name);
}
