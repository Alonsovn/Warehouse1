package com.warehouse.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.app.model.OrderInfo;
import com.warehouse.app.service.OrderInfoService;

@RequestMapping("w1/order")
@RestController
public class OrderInfoController {

	@Autowired
	private OrderInfoService orderInfoService;

	@GetMapping
	public List<OrderInfo> getAllOrders() {
		return orderInfoService.getAllOrders();
	}

	@GetMapping(path = "{id}")
	public String getOrder(@PathVariable("id") Long id) {
		OrderInfo auxOrder = orderInfoService.getOrder(id);
		if (auxOrder != null) {
			return "[" + auxOrder.toString() + "]";
		} else {
			return "Not data found.";
		}
	}

	@PostMapping
	public void addOrder(@Valid @NonNull @RequestBody OrderInfo order) {
		orderInfoService.addOrder(order);
	}

	@PutMapping
	public String updateOrder(@Valid @NonNull @RequestBody OrderInfo order) {
		return orderInfoService.updateOrder(order);
	}

	@DeleteMapping(path = "{id}")
	public void deleteOrder(@PathVariable("id") int id) {
		orderInfoService.deleteOrder(id);

	}

}
