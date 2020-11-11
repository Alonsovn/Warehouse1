package com.warehouse.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.app.model.OrderInfo;

@Repository
public interface OrdeInfoRepository extends JpaRepository<OrderInfo, UUID> {

	OrderInfo findByIdOrder(Long idOrder);

}
