package com.warehouse.app.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;// = UUID.randomUUID();

	@Column(name = "weight")
	private String weight;

	@Column(name = "volume")
	private String volume;

	@Column(name = "value")
	private String value;

	// @ManyToOne//(targetEntity = OrderInfo.class, fetch = FetchType.LAZY, optional
	// = false) // (cascade = CascadeType.ALL,
	// fetch = FetchType.LAZY)
	// @JoinColumn(f referencedColumnName = "id", nullable = false)
	
	/*@ManyToOne
	@JoinColumn(name = "order_info_id", nullable = false)
	@JsonIgnore
	private OrderInfo orderInfo;
*/
	public Item() {
	}

	public Item(UUID id, String weight, String volume, String value) {
		super();
		this.id = id;
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}
	
	
	public Item( String weight, String volume, String value) {
		super();
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
/*
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
*/
	@Override
	public String toString() {
		return String.format("{\"weight\":\"%s\",\"volume\":\"%s\",\"value\":\"%s\"}", weight, volume, value);
	}

}
