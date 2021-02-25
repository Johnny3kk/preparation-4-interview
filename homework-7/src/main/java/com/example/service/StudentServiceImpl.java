package com.example.service;

import com.example.controller.repr.StudentRepr;
import com.example.persist.entity.Student;
import com.example.persist.repo.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public List<StudentRepr> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<StudentRepr> findById(Long id) {
        return studentRepository.findById(id).map(StudentRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(StudentRepr studentRepr) {
        Student student = (studentRepr.getId() != null) ? studentRepository.findById(studentRepr.getId()).orElseThrow(RuntimeException::new) : new Student();
        student.setName(studentRepr.getName());
        student.setAge(studentRepr.getAge());
        studentRepository.save(student);
    }
}
