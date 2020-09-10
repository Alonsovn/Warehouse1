package com.warehouse.app.config;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "pulsar-config")
@Configuration
public class PulsarConfig {

	private String serviceUrl;
	private String gatewayToWarehouseTopic;

	@Bean("client")
	public PulsarClient pulsarClient() throws PulsarClientException {
		return PulsarClient.builder().serviceUrl(serviceUrl).build();
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getGatewayToWarehouseTopic() {
		return gatewayToWarehouseTopic;
	}

	public void setGatewayToWarehouseTopic(String gatewayToWarehouseTopic) {
		this.gatewayToWarehouseTopic = gatewayToWarehouseTopic;
	}

}