package com.juan.curso.springboot.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.juan.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.juan.curso.springboot.di.factura.springboot_difactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

	@Bean
	List<Item> itemsInvoice() {
		return Arrays.asList(new Item(new Product("Camara Sony", 100), 2),
				new Item(new Product("Bicicleta Bianchi aro 26", 200), 3));
	}

	@Bean
	@Primary
	List<Item> itemsInvoiceOffice() {
		return Arrays.asList(new Item(new Product("Monitor Asus", 100), 2),
				new Item(new Product("Notebook Razer", 200), 3),
				new Item(new Product("Impresora HP Multifuncional", 300), 1),
				new Item(new Product("Escritorio Oficina", 400), 1),
				new Item(new Product("Silla Oficina", 100), 4)
		);
	}

}
