package com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	/*
	 * left join fetch: indica que se realice un join con la tabla de cursos y que
	 * se traiga la información de los cursos asociados al estudiante.
	 */
	@Query("select s from Student s left join fetch s.courses c where s.id = ?1")
	Optional<Student> findOneWithCourses(Long id);

}
