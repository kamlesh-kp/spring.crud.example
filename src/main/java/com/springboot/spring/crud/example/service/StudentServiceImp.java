package com.springboot.spring.crud.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.crud.example.Repository.StudentRepository;
import com.springboot.spring.crud.example.model.Student;
import com.springboot.spring.curd.example.exception.ResourceNotFoundException;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepository studentDao;

	@Override
	public List<Student> getAllStudent() {
		return studentDao.findAll();
	}

	@Override
	public Student createStudent(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Student getById(long id) {
		return studentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not find :" + id));
		
	}

	@Override
	public Student updateStudent(long id, Student student) {
		Optional<Student> studentFound = studentDao.findById(id);
		if (studentFound.isPresent()) {
			Student updateStudent = studentFound.get();
			updateStudent.setFirstName(student.getFirstName());
			updateStudent.setLastName(student.getLastName());
			updateStudent.setAge(student.getAge());
			updateStudent.setDepartment(student.getDepartment());
			updateStudent.setEmail(student.getEmail());
			return studentDao.save(updateStudent);

		} else {
			throw new ResourceNotFoundException("Student not find :");
		}

	}

	@Override
	public void deleteStudent(long id) {
		studentDao.deleteById(id);

	}

	@Override
	public List<Student> findByFirstName(String firstName) {

		return studentDao.findByFirstName(firstName);
	}

	

	@Override
	public void activetedById(long id) {

		studentDao.updateActivetedById(id);
	}

}
