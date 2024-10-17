package com.juan.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	private ClientRepository clientRepository;

	private InvoiceRepository invoiceRepository;

	private ClientDetailsRepository clientDetailsRepository;

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	public SpringbootJpaRelationshipApplication(@Autowired ClientRepository clientRepository,
			@Autowired InvoiceRepository invoiceRepository,
			@Autowired ClientDetailsRepository clientDetailsRepository,
			@Autowired StudentRepository studentRepository,
			@Autowired CourseRepository courseRepository) {
		this.clientRepository = clientRepository;
		this.invoiceRepository = invoiceRepository;
		this.clientDetailsRepository = clientDetailsRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyBidireccionalFind();
	}

	@Transactional
	public void manyToManyRemoveBidireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyBidireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);

			}

		}

	}

	@Transactional
	public void manyToManyBidireccionalRemove() {

		Student student1 = new Student("Juan", "Pastelin");
		Student student2 = new Student("Erba", "Pura");

		Course course1 = new Course("Spring Boot", "Jhon");
		Course course2 = new Course("Java", "Pepe");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);

			}

		}

	}

	@Transactional
	public void manyToManyBidireccional() {

		Student student1 = new Student("Juan", "Pastelin");
		Student student2 = new Student("Erba", "Pura");

		Course course1 = new Course("Spring Boot", "Jhon");
		Course course2 = new Course("Java", "Pepe");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Juan", "Pastelin");
		Student student2 = new Student("Erba", "Pura");

		Course course1 = new Course("Spring Boot", "Jhon");
		Course course2 = new Course("Java", "Pepe");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);

			}

		}

	}

	@Transactional
	public void manyToManyRemoveFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);

			}

		}

	}

	@Transactional
	public void manyToManyFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Juan", "Pastelin");
		Student student2 = new Student("Erba", "Pura");

		Course course1 = new Course("Spring Boot", "Jhon");
		Course course2 = new Course("Java", "Pepe");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void oneToOneBidireccionalFindById() {
		Optional<Client> clientOptional = clientRepository.findOneByAll(1L);

		clientOptional.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);

			client.setClientDetails(clientDetails);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOneBidireccional() {
		Client client = new Client("Erba", "Pura");

		ClientDetails clientDetails = new ClientDetails(true, 5000);

		// * Se omite porque se guarda en la relación bidireccional
		// clientDetailsRepository.save(clientDetails);

		client.setClientDetails(clientDetails);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void oneToOneFindById() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findById(2L);

		clientOptional.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Client client = new Client("Juan", "Pastelin");

		Invoice invoice1 = new Invoice("Compras de la casa", 2000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 2000L);

		client.addInvoice(invoice1)
				.addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClientDb = clientRepository.findOneByAll(3L);

		optionalClientDb.ifPresent(clientDb -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				// client.getInvoices().remove(invoice);
				// invoice.setClient(null);
				clientDb.removeInvoice(invoice);
				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindId() {

		Optional<Client> optionalClient = clientRepository.findOneByAll(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 2000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 2000L);

			client.addInvoice(invoice1)
					.addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOneByAll(1L);

		optionalClientDb.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				// client.getInvoices().remove(invoice);
				// invoice.setClient(null);
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindId() {

		Optional<Client> optionalClient = clientRepository.findOneByAll(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 2000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 2000L);

			client.addInvoice(invoice1)
					.addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Juan", "Pastelin");

		Invoice invoice1 = new Invoice("Compras de la casa", 2000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 2000L);

		client.addInvoice(invoice1)
				.addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", "1234");
			Address address2 = new Address("Vasco de Gama", "9875");

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);
			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address1);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Juan", "Pastelin");
		Address address1 = new Address("El verjel", "1234");
		Address address2 = new Address("Vasco de Gama", "9875");

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Client client = clientRepository.findById(1L).orElseThrow();

		Set<Address> addresses = new HashSet<>();
		addresses.add(new Address("El verjel", "1234"));
		addresses.add(new Address("Vasco de Gama", "9875"));

		client.setAddresses(addresses);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Juan", "Pastelin");
		client.getAddresses().add(new Address("El verjel", "1234"));
		client.getAddresses().add(new Address("Vasco de Gama", "9875"));

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("Juan", "Pastelin");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceBD = invoiceRepository.save(invoice);
		System.out.println(invoiceBD);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceBD = invoiceRepository.save(invoice);
			System.out.println(invoiceBD);
		}
	}

}
