package com.example.Second_PR.repo;

import com.example.Second_PR.models.Owner;
import com.example.Second_PR.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByNameContains (String name);
    List<Owner> findByName (String name);
}
