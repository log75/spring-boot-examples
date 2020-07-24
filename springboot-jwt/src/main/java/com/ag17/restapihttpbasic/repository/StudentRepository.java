package com.ag17.restapihttpbasic.repository;

import com.ag17.restapihttpbasic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alireza on 6/19/20.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
