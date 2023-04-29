package com.greatlearning.Student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.Student.dao.StudentRepository;
import com.greatlearning.Student.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	// types of injection methods in spring- by field, by constructor, by setter

	@Autowired // constructor injection
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> theStudents = studentRepository.findAll();
		return theStudents;
	}

	@Override
	public Student findById(Long theId) {
		Optional<Student> result = studentRepository.findById(theId);

		Student theStudent = null;
		if (result.isPresent()) {
			theStudent = result.get();
		} else
			throw new RuntimeException("Did not find book id- " + theId);
		return theStudent;
	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long theId) {
		studentRepository.deleteById(theId);
	}

}
