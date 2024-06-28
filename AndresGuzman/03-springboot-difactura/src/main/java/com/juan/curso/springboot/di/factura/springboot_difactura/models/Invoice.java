package com.juan.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Invoice {

	private Client client;

	@Value("${invoice.description}")
	private String description;

	private List<Item> items;

	@PostConstruct
	public void init() {
		System.out.println("Creando el componente de la factura");
		client.setName(client.getName().concat(" Pablo"));
		description = description.concat(" del cliente: ").concat(client.getName()).concat(" ")
				.concat(client.getLastName());
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destruyendo el componente de la factura");
	}

	@Autowired
	public Invoice(Client client, List<Item> items) {
		this.client = client;
		this.items = items;
	}

	public Invoice() {
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotal() {
		return items.stream().mapToInt(Item::getImporte).sum();
	}

}
