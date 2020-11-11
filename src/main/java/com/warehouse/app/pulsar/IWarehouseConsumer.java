package com.warehouse.app.pulsar;

import org.apache.pulsar.client.api.Consumer;

public interface IWarehouseConsumer {
	
	Consumer subscribeToTopic(String pulsarTopic, String pulsarSubscription);

	void receivedAndProcessMessages(Consumer consumer);

	void closeConnection();

}