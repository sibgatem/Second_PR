package com.example.Second_PR.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Длина значения находится в пределах [2, 50]")
    String name;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Breed breed;
    @NotNull
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    Integer age;
    @NotNull
    Character sex;
    @NotNull
    Boolean sick;

    @ManyToMany
    @JoinTable(name="pet_therapy",
            joinColumns=@JoinColumn(name="pet_id"),
            inverseJoinColumns=@JoinColumn(name="therapy_id"))
    private List<Therapy> therapy;

    public Pet() {
    }

    public Pet(String name, Breed breed, Integer age, Character sex, Boolean sick) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.sick = sick;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Breed getBreed() {
        return breed;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Boolean getSick() {
        return sick;
    }

    public void setSick(Boolean sick) {
        this.sick = sick;
    }

    public List<Therapy> getTherapy() {
        return therapy;
    }

    public void setTherapy(List<Therapy> therapy) {
        this.therapy = therapy;
    }
}
