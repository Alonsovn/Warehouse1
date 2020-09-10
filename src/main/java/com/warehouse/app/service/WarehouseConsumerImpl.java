package com.warehouse.app.service;

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
import com.google.gson.reflect.TypeToken;
import com.warehouse.app.model.Warehouse;

@Service
public class WarehouseConsumerImpl implements IWarehouseConsumer {

	private final String applicationName;
	private final PulsarClient pulsarClient;
	@Autowired
	private WarehouseService warehouseService;

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
		Gson gson = new Gson();
		Type type = new TypeToken<List<Warehouse>>() {
		}.getType();
		List<Warehouse> warehouseList = gson.fromJson(message, type);

		for (Warehouse warehouse : warehouseList) {
			if (warehouse != null) {
				System.out.println("Warehouse " + warehouse.toString());
				warehouseService.addWarehouse(warehouse);
			}
		}
	}

}