package com.example.Second_PR.models;

import javax.persistence.*;
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
    Double salary;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="depart_id")
    private Depart depart;



    public Owner() {
    }

    public Owner(String name, String post, Character sex, Integer age, Double salary, Depart department) {
        this.name = name;
        this.post = post;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
        this.depart = department;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
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
