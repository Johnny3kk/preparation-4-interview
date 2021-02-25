package com.example.controller;

import com.example.controller.repr.StudentRepr;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public String allStudents(Model model) {
        model.addAttribute("studentsPage", studentService.findAll());
        return "students";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        StudentRepr studentRepr = new StudentRepr();
        model.addAttribute("student", studentRepr);
        return "student";
    }

    @PostMapping("student/insert")
    public String insertStudent(Model model, StudentRepr studentRepr) {
        studentService.save(studentRepr);
        return "redirect:/students";
    }

    @GetMapping("students/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        StudentRepr studentRepr = studentService.findById(id).get();
        model.addAttribute("student", studentRepr);
        return "student";
    }

    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

}
