package com.greatlearning.Student.service;

import java.util.List;

import com.greatlearning.Student.entity.Student;

public interface StudentService {

	public List<Student> findAllStudents();
	
	public Student findById(Long theId);
	
	public void save(Student theStudent);
	
	public void deleteStudentById(Long theId);

	public Student updateStudent(Student student);
}
