package com.hellokoding.auth.controller;


import com.hellokoding.auth.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alireza on 4/20/20.
 */
@RestController
@RequestMapping(value = "/management/api/v1/students")
public class StudentManagementController {

    private List<Student> students = List.of(
            new Student(1, "jack jackson"),
            new Student(2, "niki jackson"),
            new Student(3, "rose jackson"),
            new Student(4, "melania jackson")
    );


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public List<Student> getAllStudent() {
        return students;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void addStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("id") String id) {
        System.out.println("delete student where id = " + id);
    }

    @PutMapping(value = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        System.out.println("update student where id = " + id + student);
    }
}
