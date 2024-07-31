package com.mehdox.springbackside.repository;

import com.mehdox.springbackside.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByCode(String student_code);
}
