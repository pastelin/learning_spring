package com.juan.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.juan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.juan.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.juan.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	private PersonRepository repository;
	private Scanner scanner = new Scanner(System.in);

	public SpringbootJpaApplication(PersonRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		subQueries();
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("==================== Consulta por el nombre más corto y su largo ====================");
		List<Object[]> shorterName = repository.getShorterName();
		shorterName.forEach(p -> System.out.println("Nombre: " + p[0] + ", longitud: " + p[1]));

		System.out.println("==================== Consulta para obtener el último registro de persona ====================");
		Optional<Person> lastPerson = repository.getLastRegistration();
		lastPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		Long count = repository.getTotalPerson();
		Long minId = repository.getMinId();
		Long maxId = repository.getMaxId();

		System.out.println("Total de personas: " + count);
		System.out.println("Id mínimo: " + minId);
		System.out.println("Id máximo: " + maxId);

		System.out.println("Consulta de nombres y longitud de nombres");
		List<Object[]> personNameLength = repository.getPersonNameLength();
		personNameLength.forEach(p -> System.out.println("Nombre: " + p[0] + ", longitud: " + p[1]));


		System.out.println("Consultas resumen de funciones de agreagación");
		Object[] resume = (Object[]) repository.getResumeAggregatonFunction();
		System.out.println("Min id: " + resume[0]);
		System.out.println("Max id: " + resume[1]);
		System.out.println("Sum id: " + resume[2]);
		System.out.println("Promedio longitud nombre: " + resume[3]);
		System.out.println("Total de personas: " + resume[4]);

		
	}

	@Transactional(readOnly = true)
	public void personaliedQueriesBetween() {
		System.out.println("==================== Consultas por rangos ====================");
		List<Person> persons = repository.findAllBetweenId(1, 5);
		persons.forEach(System.out::println);

		System.out.println("==================== Consultas por rangos de nombre ====================");
		persons = repository.findAllBetweenName("A", "B");
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("==================== Consulta nombres y apellidos de personas ====================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("==================== Consulta nombres y apellidos mayusculas ====================");
		List<String> namesUpper = repository.findAllFullNameConcatUpper();
		namesUpper.forEach(System.out::println);

		System.out.println("==================== Consulta nombres y apellidos de minusculas ====================");
		List<String> namesLower = repository.findAllFullNameConcatLower();
		namesLower.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("====================Consultas con nombres de personas====================");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("==================== Consultas con nombres únicos de personas ====================");
		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(System.out::println);

		System.out.println("============= Consultas con lenguajes de programación únicos de personas ==============");
		List<String> programmingDistinct = repository.findAllProgrammingLanguageDistinct();
		programmingDistinct.forEach(System.out::println);

		System.out.println("============= Consultas del # de lenguajes de programación únicos ==============");
		System.out.println(repository.findCountProgrammingLanguageDistinct());

	}

	@Transactional
	public void personalizedQueries2() {
		System.out.println(
				"==================== Consulta por objeto persona y lenguaje de programación =================");
		List<Object[]> personRegs = repository.findAllMixPerson();

		personRegs.forEach(reg -> System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]));

		System.out.println(
				"==================== Consulta que puebla y devuelve objeto entity de una instancia personalizada =================");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("Consulta que puebla y devuelve objeto dto de una clase personalizada");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional
	public void personalizedQueries() {

		System.out.println("==================== Consulta solo el nombre por el id =================");

		System.out.println("Ingrese el id de la persona a buscar:");
		Long id = scanner.nextLong();
		scanner.close();

		String name = repository.getNameById(id);
		System.out.println("======== Mostrando solo el nombre =======");
		System.out.println(name);

		String fullName = repository.getFullNameById(id);
		System.out.println("======== Mostrando solo el fullname =======");
		System.out.println(fullName);

		System.out.println("Consulta or campos personalizados lista");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out
				.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));

	}

	@Transactional
	public void deleteByEntity() {
		System.out.println("Ingrese el id de la persona a eliminar:");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(repository::delete, () -> System.out.println("La persona no existe"));
		scanner.close();
	}

	@Transactional
	public void deleteById() {
		System.out.println("Ingrese el id de la persona a eliminar:");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		scanner.close();
	}

	@Transactional
	public void update() {
		System.out.println("Ingrese el id de la persona a actualizar:");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresent(p -> {
			System.out.println(p);
			System.out.println("Ingrese el lenguaje de programación:");
			String programmingLanguage = scanner.next();
			p.setProgrammingLanguage(programmingLanguage);

			Person personDb = repository.save(p);
			System.out.println(personDb);
		});
	}

	@Transactional
	public void create() {

		System.out.println("Ingrese el nombre:");
		String name = scanner.nextLine();
		System.out.println("Ingrese el apellido:");
		String lastname = scanner.nextLine();
		System.out.println("Ingrese el lenguaje de programación:");
		String programmingLanguage = scanner.nextLine();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);

		// if (optionalPerson.isPresent()) {
		// person = optionalPerson.get();
		// }

		// System.out.println(person);

		repository.findByNameContaining("oe").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = repository.findByProgrammingLanguage("java");
		// List<Person> persons = repository.buscarByProgrammingLanguage("java",
		// "Andres");
		List<Person> persons = repository.findByProgrammingLanguageAndName("java", "Andres");
		persons.stream().forEach(System.out::println);

		List<Object[]> personData = repository.obtenerPersonData("java", "Andres");
		personData.stream().forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
	}

}
