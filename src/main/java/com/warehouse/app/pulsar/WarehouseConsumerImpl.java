package com.warehouse.app.pulsar;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.warehouse.app.model.Item;
import com.warehouse.app.model.Order;
import com.warehouse.app.model.OrderInfo;
import com.warehouse.app.model.Transport;
import com.warehouse.app.model.UserInfo;
import com.warehouse.app.service.OrderInfoService;

@Service
public class WarehouseConsumerImpl implements IWarehouseConsumer {

	private final String applicationName;
	private final PulsarClient pulsarClient;
	@Autowired
	private OrderInfoService orderInfoService;

	public WarehouseConsumerImpl(@Value("${application.name}") final String applicationName,
			final PulsarClient pulsarClient) {
		this.applicationName = applicationName;
		this.pulsarClient = pulsarClient;
	}

	public void receivedAndProcessMessages(Consumer consumer) {

		System.out.println("Starting Pulsar listener");
		while (true) {
			try {
				Message msg = consumer.receive();
				String messageContent = new String(msg.getData());
				System.out.printf("Warehouse Consumer (Pulsar). Message received: %s", messageContent);
				 processReceivedMessage(messageContent);
				consumer.acknowledge(msg);
			} catch (PulsarClientException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Could not process the message: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public Consumer subscribeToTopic(String pulsarTopic, String pulsarSubscription) {

		Consumer consumer = null;
		try {
			consumer = pulsarClient.newConsumer().topic(pulsarTopic).subscriptionName(pulsarSubscription)
					.subscriptionType(SubscriptionType.Failover).subscribe();
		} catch (PulsarClientException e1) {
			e1.printStackTrace();
		}
		return consumer;

	}

	public void closeConnection() {
		try {
			pulsarClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void processReceivedMessage(String message) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		//Gson gson = new Gson();
		Type type = new TypeToken<List<Order>>() {
		}.getType();
		List<Order> orderList = gson.fromJson(message, type);
		for (Order order : orderList) {
			if (order != null) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setIdOrder(order.getId());
				orderInfo.setUnits(order.getUnits());
				orderInfo.setDeliveryDate(order.getDeliveryDate());
				orderInfo.setTransport(new Transport(order.getTransportType()));
				orderInfo.setUser(new UserInfo(order.getUser()));
				orderInfo.getItems().add(new Item(order.getWeight(), order.getVolume(), order.getValue()));
				System.out.println("\nWarehouse " + orderInfo.toString());
				orderInfoService.addOrder(orderInfo);
			}
		}
	}

}