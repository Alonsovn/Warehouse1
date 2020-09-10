package com.warehouse.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse")
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_serial", columnDefinition = "serial")
	private int idSerial;

	@Column(name = "id")
	private int id;

	@Column(name = "weight")
	private String weight;

	@Column(name = "volume")
	private String volume;

	@Column(name = "value")
	private String value;

	@Column(name = "units")
	private int units;

	@Column(name = "transport_type")
	private String transportType;

	@Column(name = "warehouse")
	private String warehouse;

	@Column(name = "wh_user")
	private int user;

	@Column(name = "delivery_date")
	private String deliveryDate;

	public Warehouse() {
	}

	public Warehouse(int idSerial, int id, String weight, String volume, String value, int units, String transportType,
			String warehouse, int user, String deliveryDate) {
		super();
		this.idSerial = idSerial;
		this.id = id;
		this.weight = weight;
		this.volume = volume;
		this.value = value;
		this.units = units;
		this.transportType = transportType;
		this.warehouse = warehouse;
		this.user = user;
		this.deliveryDate = deliveryDate;
	}
/*
	public int getIdSerial() {
		return idSerial;
	}*/
	
	public int returnIdSerial() {
		return idSerial;
	}

	public void setIdSerial(int uuid) {
		this.idSerial = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return " {\"id\":" + id + ", \"weight\":\"" + weight + "\", \"volume\":\"" + volume + "\", \"value\":\"" + value
				+ "\", \"units\":" + units + ", \"transportType\":\"" + transportType + "\", \"warehouse\":\""
				+ warehouse + "\", \"user\":" + user + ", \"deliveryDate\":\"" + deliveryDate + "\"}";
	}

}
