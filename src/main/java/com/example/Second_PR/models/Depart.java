package com.example.Second_PR.models;

import javax.persistence.*;


@Entity
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(optional = true, mappedBy = "depart")
    private Owner people;

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

    public Owner getPeople() {
        return people;
    }

    public void setPeople(Owner people) {
        this.people = people;
    }
}
