package com.greatlearning.Student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.Student.entity.Student;
import com.greatlearning.Student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudent = studentService.findAllStudents();
		theModel.addAttribute("student",theStudent);
		return "list";
	}
	 
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student",theStudent);
		return "create_student";
	}
	
	@PostMapping("/{id}")
	public String updatestudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {

		// get student from database by id
		Student existingStudent = studentService.findById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setCourse(student.getCourse());
		existingStudent.setCountry(student.getCountry());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/student/list";
	}
	
	@PostMapping("/list")
	public String save(@ModelAttribute("student")Student theStudent) {
		studentService.save(theStudent);
		return "redirect:/student/list";
	}
	
	@RequestMapping("/edit/{id}")
	public String editstudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.findById(id));
		return "edit_student";
	}
	
	@RequestMapping("/{id}")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/student/list";
	}
	
	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		
		ModelAndView model = new ModelAndView();
		if(user != null) {
			model.addObject("msg","Hi "+user.getName()+" you do not have permission to access this page");
		}
		else {
			model.addObject("msg","You do not have permission to access this page");
		}
		model.setViewName("403");
		return model;
	}
}
