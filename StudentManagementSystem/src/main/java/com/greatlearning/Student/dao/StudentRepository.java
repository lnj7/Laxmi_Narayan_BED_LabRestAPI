package com.greatlearning.Student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.Student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
