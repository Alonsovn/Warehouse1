package com.warehouse.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.app.model.OrderInfo;
import com.warehouse.app.model.Transport;
import com.warehouse.app.model.UserInfo;
import com.warehouse.app.repository.ItemRepository;
import com.warehouse.app.repository.OrdeInfoRepository;
import com.warehouse.app.repository.TransportRepository;
import com.warehouse.app.repository.UserInfoRepository;

@Service
public class OrderInfoService {

	private final Logger logger = LoggerFactory.getLogger(OrderInfoService.class);

	@Autowired
	OrdeInfoRepository orderInfoRepo;
	@Autowired
	TransportRepository transRepo;
	@Autowired
	UserInfoRepository userRepo;
	@Autowired
	ItemRepository itemRepo;

	@Autowired
	UserInfoRepository userInfoRepo;

	public List<OrderInfo> getAllOrders() {
		return orderInfoRepo.findAll();
	}

	public OrderInfo getOrder(Long id) {
		
		return orderInfoRepo.findByIdOrder(id);
		/*
		 * try { return orderInfoRepository.findById(id).get(); } catch (Exception e) {
		 * System.out.println(e.getCause()); return null; }
		 */
		//return null;
	}

	public void addOrder(OrderInfo order) {

		logger.info("Comming Order--> " + order.toString());
		OrderInfo tempOrder = orderInfoRepo.findByIdOrder(order.getIdOrder());
		if (tempOrder != null) {
			order.setId(tempOrder.getId());
		}		
		
		Transport tempTransport = transRepo.findByTransportType(order.getTransport().getTransportType());
		if (tempTransport != null) {
			order.setTransport(tempTransport);
		}
		
		UserInfo tempUser = userRepo.findByIdUser(order.getUser().getIdUser());
		if (tempUser != null) {
			order.setUser(tempUser);
		}
		
		try {
			orderInfoRepo.save(order);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		

		// List<Item> itemList = order.getItems();
		// OrderInfo tempOrder = order;
		// tempOrder.getItems().add(order.getItems().get(0) );
		// orderInfoRepository.save(order);
		// logger.info("Temp Order--> " + order.toString());

		/*
		 * try { List<OrderInfo> allOrders = getAllOrders(); for (OrderInfo auxOrder :
		 * allOrders) { if (auxOrder.getIdOrder() == order.getIdOrder()) {
		 * order.setId(auxOrder.getId());
		 * order.getTransport().setId(auxOrder.getTransport().returnId());
		 * //order.getUserInfo().setId(auxOrder.getUserInfo().returnId());
		 * orderInfoRepository.save(order); return; } } orderInfoRepository.save(order);
		 * 
		 * } catch (Exception e) { System.out.println(e.getMessage()); }
		 */
	}

	public String updateOrder(OrderInfo order) {

		String result = "Not record found";
		List<OrderInfo> allOrders = getAllOrders();
		for (OrderInfo auxOrder : allOrders) {
			if (auxOrder.getIdOrder() == order.getIdOrder()) {
				order.setId(auxOrder.getId());
				order.getTransport().setId(auxOrder.getTransport().getId());
				// order.getUserInfo().setId(auxOrder.getUserInfo().returnId());
				orderInfoRepo.save(order);
				return "Data updated.";
			}
		}
		return result;

	}

	public void deleteOrder(int id) {
		/*
		 * try { OrderInfo auxOrder = orderInfoRepository.findById(id).get(); if
		 * (auxOrder != null) { orderInfoRepository.deleteById(id); } } catch (Exception
		 * e) { System.out.println(e.getMessage()); }
		 */
	}

}
