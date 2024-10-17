package com.juan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

	@Query("select c from Client c left join fetch c.addresses where c.id=?1")
	Optional<Client> findOne(long id);

	@Query("select c from Client c left join fetch c.invoices where c.id=?1")
	Optional<Client> findOneWithInvoices(long id);
	
	/*
	 * @Query: Permite definir una consulta personalizada
	 * left join fetch: Permite traer los registros de la relación de manera inmediata siempre y cuando existan registros
	 * Al utilizar multiple left join fetch, la información no se puede almacenar con ArrayList, para ello se debe utilizar Set
	 */
	@Query("select c from Client c left join fetch c.invoices left join fetch c.addresses left join fetch c.clientDetails where c.id=?1")
	Optional<Client> findOneByAll(long id);

}
