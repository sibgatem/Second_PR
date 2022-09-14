package com.example.Second_PR.repo;

import com.example.Second_PR.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PerRepo extends CrudRepository<Pet, Long> {
    Pet findByName (String name);
}
