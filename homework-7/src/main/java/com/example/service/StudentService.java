package com.example.service;

import com.example.controller.repr.StudentRepr;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentRepr> findAll();

    Optional<StudentRepr> findById(Long id);

    void deleteById(Long id);

    void save(StudentRepr studentRepr);
}
