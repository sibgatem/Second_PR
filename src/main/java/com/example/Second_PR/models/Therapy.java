package com.example.Second_PR.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Therapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany
    @JoinTable (name="pet_therapy",
            joinColumns=@JoinColumn (name="therapy_id"),
            inverseJoinColumns=@JoinColumn(name="pet_id"))
    private List<Pet> pets;


    public Therapy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
