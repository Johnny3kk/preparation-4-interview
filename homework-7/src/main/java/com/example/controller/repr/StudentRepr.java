package com.example.controller.repr;

import com.example.persist.entity.Student;

import java.io.Serializable;

public class StudentRepr implements Serializable {

    private Long id;

    private String name;

    private int age;

    public StudentRepr() {
    }

    public StudentRepr(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
