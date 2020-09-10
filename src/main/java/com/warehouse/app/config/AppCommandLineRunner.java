package com.warehouse.app.config;

import org.apache.pulsar.client.api.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.warehouse.app.service.IWarehouseConsumer;

@ConfigurationProperties(prefix = "pulsar-config")
@Component
@Order(1)
public class AppCommandLineRunner implements CommandLineRunner {

	private String subscription;
	private String gatewayToWarehouseTopic;

	@Autowired
	private IWarehouseConsumer pulsarService;

	@Override
	public void run(String... args) throws Exception {
		Consumer warehouseConsumer = pulsarService.subscribeToTopic(gatewayToWarehouseTopic, subscription);
		pulsarService.receivedAndProcessMessages(warehouseConsumer);
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getGatewayToWarehouseTopic() {
		return gatewayToWarehouseTopic;
	}

	public void setGatewayToWarehouseTopic(String gatewayToWarehouseTopic) {
		this.gatewayToWarehouseTopic = gatewayToWarehouseTopic;
	}

}