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

import com.warehouse.app.model.Warehouse;
import com.warehouse.app.service.WarehouseService;

@RequestMapping("/warehouse1")
@RestController
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	

	@GetMapping
	public List<Warehouse> getAllWarehouse() {
		return warehouseService.getAllWarehouse();
	}

	@GetMapping(path = "{id}")
	public Warehouse getWarehouse(@PathVariable("id") int id) {
		return warehouseService.getWarehouse(id);
	}

	@PostMapping
	public void addWarehouse(@Valid @NonNull @RequestBody Warehouse warehouse) {
		warehouseService.addWarehouse(warehouse);
	}

	@PutMapping
	public void updateWarehouse(@Valid @NonNull @RequestBody Warehouse warehouse) {
		warehouseService.updateWarehouse(warehouse);
	}

	@DeleteMapping(path = "{id}")
	public void deleteWarehouse(@PathVariable("id") int id) {
		warehouseService.deleteWarehouse(id);

	}

}
