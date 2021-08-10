package com.springboot.spring.crud.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.spring.crud.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	@Transactional
	@Modifying
	@Query("update Student set activeted=true where id=:id")
	void updateActivetedById(long id);

	Student findByEmail(String email);

	List<Student> findByFirstName(String firstName);

	Student findByFirstNameAndLastName(String firstName, String lastName);

	List<Student> findByAge(int age);

	List<Student> findByFirstNameLike(String firstName);

}
