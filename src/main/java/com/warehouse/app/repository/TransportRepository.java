package com.warehouse.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.app.model.Transport;

@Repository
public interface TransportRepository extends JpaRepository<Transport, UUID> {

	Transport findByTransportType(String transportType);
}
