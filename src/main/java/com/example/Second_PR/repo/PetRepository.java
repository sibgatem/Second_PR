package com.example.Second_PR.repo;

import com.example.Second_PR.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository <Pet, Long> {
    List<Pet> findByNameContains (String name);
    List<Pet> findByName (String name);
}
