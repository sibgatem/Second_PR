package com.example.Second_PR.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Длина значения находится в пределах [2, 50]")
    String name, breed;
    @NotNull
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    Integer age;
    @NotNull
    Character sex;
    @NotNull
    Boolean sick;

    public Pet() {
    }

    public Pet(String name, String breed, Integer age, Character sex, Boolean sick) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.sick = sick;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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
}
