package com.example.Second_PR.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Длина значения находится в пределах [2, 50]")
    String name, post;
    @NotNull
    Character sex;
    @NotNull
    @Min(value = 0, message = "Поле")
    Integer age;
    @NotNull
    @Positive
    Double salary;

    public Owner() {
    }

    public Owner(String name, String post, Integer age, Character sex, Double salary) {
        this.name = name;
        this.post = post;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
