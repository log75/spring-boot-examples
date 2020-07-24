package com.ag17.restapihttpbasic.controller;

import com.ag17.restapihttpbasic.exception.excep.EntityNotFoundException;
import com.ag17.restapihttpbasic.model.Student;
import com.ag17.restapihttpbasic.repository.StudentRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alireza on 6/19/20.
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping
    public List<Student> getAllStudents(Principal principal) {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("haha"));

    }

    @PostMapping
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public Student postStudent(@RequestBody @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalStateException();
        }
        new ResponseEntity<Student>(student, new HttpHeaders(), HttpStatus.OK);
        return studentRepository.save(student);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public Student putStudent(@PathVariable("id") Long id, @RequestBody @Valid Student uStudent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalStateException();
        }
        Student student = studentRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        student.setFirstName(uStudent.getFirstName());
        student.setLastName(uStudent.getLastName());
        student.setAge(uStudent.getAge());
        return studentRepository.save(student);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
    }

}
