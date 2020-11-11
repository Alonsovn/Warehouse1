package com.warehouse.app.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;// = UUID.randomUUID();

	@Column(name = "id_user")
	private int idUser;

	public UserInfo() {
	}

	public UserInfo(UUID id, int idUser) {
		super();
		this.id = id;
		this.idUser = idUser;
	}

	public UserInfo(int idUser) {
		super();
		this.idUser = idUser;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return String.format("{\"idUser\":%d}", idUser);
	}

}
