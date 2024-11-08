package com.juan.curso.springboot.jpa.springboot_jpa_relationship.entities;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String lastname;

	/*
	 * OneToOne: es una relación uno a uno, es decir, un cliente tiene un solo
	 * detalle de cliente.
	 * OneToOne: por defecto es EAGER, es decir, carga los datos de manera inmediata
	 * 
	 * CascadeType.ALL: Indica que se aplicarán todas las operaciones de la entidad
	 * padre a la entidad hija (persist, remove, merge, refresh)
	 * 
	 * orphanRemoval = true: Elimina los registros huérfanos es decir, los registros
	 * que no tienen una relación con el cliente
	 * 
	 * mappedBy = "client": Indica que la relación está mapeada en la clase
	 * ClientDetails
	 * 
	 * 
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
	private ClientDetails clientDetails;

	// @JoinColumn(name = "client_id")
	/*
	 * @OneToMany: Indica que la relación es de uno a muchos
	 * 
	 * cascade = CascadeType.ALL: Indica que se aplicarán todas las operaciones de
	 * la entidad padre a la entidad hija (persist, remove, merge, refresh)
	 * 
	 * Cuando se define una lista con @OneToMany, se debe inicializar la lista
	 * 
	 * orphanRemoval = true: Elimina los registros huérfanos es decir, los
	 * registros que no tienen una relación con el cliente
	 * 
	 * @JoinTable: Permite definir una tabla intermedia para la relación
	 * 
	 * fetch = FetchType.EAGER: Carga los registros de la lista de direcciones de
	 * manera inmediata, NO ES RECOMENDABLE
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "tbl_clientes_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_direccion"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_direccion" }))
	private Set<Address> addresses;

	// * mappedBy = "client": Indica que la relación está mapeada en la clase
	// Invoice
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
	private Set<Invoice> invoices;

	public Client() {
		this.addresses = new HashSet<>();
		this.invoices = new HashSet<>();
	}

	public Client(String name, String lastname) {
		this();
		this.name = name;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Client addInvoice(Invoice invoice) {
		invoices.add(invoice);
		invoice.setClient(this);

		return this;
	}

	public void removeInvoice(Invoice invoice) {
		this.getInvoices().remove(invoice);
		invoice.setClient(null);
	}

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
		clientDetails.setClient(this);
	}

	public void removeClientDetails(ClientDetails clientDetails) {
		clientDetails.setClient(null);
		this.clientDetails = null;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", lastname=" + lastname + ", clientDetails=" + clientDetails + "}";
	}

}
