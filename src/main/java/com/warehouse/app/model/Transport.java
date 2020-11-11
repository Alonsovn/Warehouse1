package com.warehouse.app.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transport")
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;// = UUID.randomUUID();

	@Column(name = "transport_type")
	private String transportType;

	public Transport() {
	}

	public Transport(UUID id, String transportType) {
		super();
		this.id = id;
		this.transportType = transportType;
	}

	public Transport(String transportType) {
		super();
		this.transportType = transportType;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	@Override
	public String toString() {
		return String.format("{\"transportType\":\"%s\"}", transportType);

	}

}
