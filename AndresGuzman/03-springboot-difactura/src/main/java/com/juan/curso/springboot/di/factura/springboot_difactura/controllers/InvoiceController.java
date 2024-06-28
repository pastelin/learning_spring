package com.juan.curso.springboot.di.factura.springboot_difactura.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.di.factura.springboot_difactura.models.Invoice;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	private Invoice invoice;

	public InvoiceController(Invoice invoice) {
		this.invoice = invoice;
	}

	@GetMapping("/show")
	public Invoice show() {
		return invoice;
	}

}
