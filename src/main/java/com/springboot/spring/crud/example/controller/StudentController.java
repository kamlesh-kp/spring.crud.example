package com.springboot.spring.crud.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.spring.crud.example.dto.StudentDto;
import com.springboot.spring.crud.example.model.Student;
import com.springboot.spring.crud.example.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StudentService studentService;

	@GetMapping("/getall")
	public List<StudentDto> getAllStudent() {
		return studentService.getAllStudent().stream().map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
		
	}

	@PostMapping("/addnew")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {

		Student StudentRequest = modelMapper.map(studentDto, Student.class);
		Student student = studentService.createStudent(StudentRequest);
		StudentDto StudentResponse = modelMapper.map(student, StudentDto.class);
		return ResponseEntity.ok().body(StudentResponse);

	}

	@GetMapping("/find/{id}")
	public ResponseEntity<StudentDto> getById(@PathVariable long id) {

		Student student = studentService.getById(id);
		StudentDto StudentResponse = modelMapper.map(student, StudentDto.class);
		return ResponseEntity.ok().body(StudentResponse);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {
		Student StudentRequest = modelMapper.map(studentDto, Student.class);
		Student student = studentService.updateStudent(id, StudentRequest);
		StudentDto StudentResponse = modelMapper.map(student, StudentDto.class);
		return ResponseEntity.ok().body(StudentResponse);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentService.deleteStudent(id);
	}

	@PutMapping("/updated/{id}")
	public void activetedById(@PathVariable long id) {
		studentService.activetedById(id);

	}

	@GetMapping("/firstName/{firstName}")
	public ResponseEntity<StudentDto> findByFirstName(@PathVariable String firstName) {

		List<Student> student = studentService.findByFirstName(firstName);
		StudentDto StudentResponse = modelMapper.map(student, StudentDto.class);
		return ResponseEntity.ok().body(StudentResponse);

	}

}
