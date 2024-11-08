package com.juan.curso.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

	@Column(name = "create_at")
	private LocalDateTime createAt;

	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	@PrePersist
	public void prePersist() {
		System.out.println("Evento del ciclo de vida del entity antes de ser persistido");
		this.createAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("Evento del ciclo de vida del entity antes de ser actualizado");
		this.updateAt = LocalDateTime.now();
	}
}
