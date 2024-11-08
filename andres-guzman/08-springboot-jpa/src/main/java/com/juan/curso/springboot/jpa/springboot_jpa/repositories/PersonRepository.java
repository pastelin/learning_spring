package com.juan.curso.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.juan.curso.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

	@Query("select p from Person p where p.id in ?1")
	List<Person> getPersonsById(List<Long> ids);

	@Query("select p from Person p where p.id = (select max(p.id) from Person p) ")
	public Optional<Person> getLastRegistration();

	@Query("select p.name, length(p.name) from Person p where length(p.name) = ( select min(length(p.name)) from Person p )")
	public List<Object[]> getShorterName();

	@Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
	public Object getResumeAggregatonFunction();

	@Query("select min(length(p.name)) from Person p")
	public Integer getMinLengthName();

	@Query("select max(length(p.name)) from Person p")
	public Integer getMaxLengthName();

	@Query("select p.name, length(p.name) from Person p")
	public List<Object[]> getPersonNameLength();

	@Query("select count(p) from Person p")
	Long getTotalPerson();

	@Query("select min(p.id) from Person p")
	Long getMinId();

	@Query("select max(p.id) from Person p")
	Long getMaxId();

	List<Person> findAllByOrderByNameDesc();

	@Query("select p from Person p order by p.name asc")
	List<Person> getAllOrdered();

	List<Person> findByIdBetweenOrderByNameDesc(Long id1, Long id2);

	List<Person> findByNameBetweenOrderByNameAscLastnameDesc(String name1, String name2);

	@Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastname desc")
	List<Person> findAllBetweenName(String c1, String c2);

	@Query("select p from Person p where p.id between ?1 and ?2 order by p.name asc")
	List<Person> findAllBetweenId(Integer id1, Integer id2);

	@Query("select lower(p.name || ' ' || p.lastname) from Person p")
	List<String> findAllFullNameConcatLower();

	@Query("select upper(concat(p.name, ' ', p.lastname)) from Person p")
	List<String> findAllFullNameConcatUpper();

	// @Query("select concat(p.name, ' ', p.lastname) from Person p")
	@Query("select p.name || ' ' || p.lastname from Person p")
	List<String> findAllFullNameConcat();

	@Query("select p.name from Person p")
	List<String> findAllNames();

	@Query("select distinct(p.name) from Person p")
	List<String> findAllNamesDistinct();

	@Query("select distinct(p.programmingLanguage) from Person p")
	List<String> findAllProgrammingLanguageDistinct();

	@Query("select count(distinct(p.programmingLanguage)) from Person p")
	int findCountProgrammingLanguageDistinct();

	@Query("select new com.juan.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
	List<PersonDto> findAllPersonDto();

	@Query("select new Person(p.name, p.lastname) from Person p")
	List<Person> findAllObjectPersonPersonalized();

	@Query("select p.name from Person p where p.id = ?1")
	String getNameById(Long id);

	@Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id = ?1")
	String getFullNameById(Long id);

	@Query("select p from Person p where p.id = ?1")
	Optional<Person> findOne(Long id);

	@Query("select p from Person p where p.name = ?1")
	Optional<Person> findOneName(String name);

	@Query("select p from Person p where p.name like %?1%")
	Optional<Person> findOneLikeName(String name);

	Optional<Person> findByNameContaining(String name);

	List<Person> findByProgrammingLanguage(String programmingLanguage);

	@Query("select p from Person p where p.programmingLanguage = ?1 and p.name = ?2")
	List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

	List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

	@Query("select p, p.programmingLanguage from Person p")
	List<Object[]> findAllMixPerson();

	@Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
	List<Object[]> obtenerPersonDataList();

	@Query("select p.name, p.programmingLanguage from Person p")
	List<Object[]> obtenerPersonData();

	@Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
	List<Object[]> obtenerPersonDataFull();

	@Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id = ?1")
	List<Object[]> obtenerPersonDataFullById();

	@Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage = ?1 and p.name = ?2")
	List<Object[]> obtenerPersonData(String programmingLanguage, String name);
}
