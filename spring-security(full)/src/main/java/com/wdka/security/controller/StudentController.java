package com.wdka.security.controller;

import com.wdka.security.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alireza on 4/19/20.
 */
@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {

    private List<Student> students = List.of(
            new Student(1, "leo1 jackson"),
            new Student(2, "leo2 jackson"),
            new Student(3, "leo3 jackson"),
            new Student(4, "leo4 jackson")
    );

    @GetMapping(value = "/{studentId}")
    public Student getStudent(@PathVariable(name = "studentId") Integer id) {

        return students.stream().filter(student ->
                student.getStudentId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("student " + id + " not exist"));

    }

}
