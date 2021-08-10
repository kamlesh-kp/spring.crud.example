package com.springboot.spring.crud.example.service;

import java.util.List;

import com.springboot.spring.crud.example.model.Student;

public interface StudentService {
	List<Student> getAllStudent();

	Student createStudent(Student student);

	Student getById(long id);

	void deleteStudent(long id);

	Student updateStudent(long id, Student student);

	List<Student> findByFirstName(String firstName);

	void activetedById(long id);

}