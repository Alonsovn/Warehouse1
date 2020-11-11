package com.warehouse.app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_info")
public class OrderInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;// = UUID.randomUUID();

	@Column(name = "id_order")
	private Long idOrder;

	@Column(name = "units")
	private int units;

	@Column(name = "delivery_date")
	private Date deliveryDate;

	@OneToOne(targetEntity = Transport.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "transport_id", referencedColumnName = "id")
	private Transport transport;

	@OneToOne(targetEntity = UserInfo.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_info_id", referencedColumnName = "id")
	private UserInfo user;

	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_info_id", referencedColumnName = "id")
	private List<Item> items;

	public OrderInfo() {
		items = new ArrayList<Item>();
	}

	public OrderInfo(UUID id, Long idOrder, int units, Date deliveryDate, Transport transport, List<Item> items,
			UserInfo user) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.units = units;
		this.deliveryDate = deliveryDate;
		this.transport = transport;
		this.items = items;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return String.format(
				"{\"idOrder\":%d," + "\"units\":%d,"
						+ "\"deliveryDate\":\"%s\",\"transport\":%s,\"user\":%s,\"items\":%s}",
				idOrder, units, dateFormat.format(deliveryDate), transport.toString(), user.toString(),
				items.toString());
	}
}
