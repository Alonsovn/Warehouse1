package com.warehouse.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.app.model.Warehouse;
import com.warehouse.app.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	WarehouseRepository warehouseRepository;

	public List<Warehouse> getAllWarehouse() {
		return warehouseRepository.findAll();
	}

	public Warehouse getWarehouse(int id) {

		List<Warehouse> allWarehouse = getAllWarehouse();	
		for (Warehouse warehouse : allWarehouse) {
			if (warehouse.getId() == id) {
				return warehouse;
			}
		}
		return null;
		// return warehouseRepository.findById(id).get();
	}

	public void addWarehouse(Warehouse warehouse) {
		try {
			List<Warehouse> allWarehouse = getAllWarehouse();
			for (Warehouse wh : allWarehouse) {
				if (wh.getId() == warehouse.getId()) {
					warehouse.setIdSerial(wh.returnIdSerial());
					warehouseRepository.save(warehouse);
					return;
				}
			}
			warehouseRepository.save(warehouse);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// warehouseRepository.save(warehouse);
	}

	public void updateWarehouse(Warehouse warehouse) {

		/*
		 * if (wh != null) { 
		 * 
		 * warehouseRepository.save(wh); }
		 */

		List<Warehouse> allWarehouse = warehouseRepository.findAll();
		for (Warehouse wh : allWarehouse) {
			if (wh.getId() == warehouse.getId()) {
				warehouse.setIdSerial(wh.returnIdSerial());
				warehouseRepository.save(warehouse);
				return;
			}
		}

	}

	public void deleteWarehouse(int id) {

		List<Warehouse> allWarehouse = warehouseRepository.findAll();
		for (Warehouse warehouse : allWarehouse) {
			if (warehouse.getId() == id) {
				warehouseRepository.deleteById(warehouse.returnIdSerial());
				return;
			}
		}
		// warehouseRepository.deleteById(id);

	}

	public Date StringToDate(String s) {

		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			result = dateFormat.parse(s);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
