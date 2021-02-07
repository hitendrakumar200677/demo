package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Shipment;
import com.example.demo.repository.ShipmentRepository;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ShipmentController {

	@Autowired
	ShipmentRepository shipmentRepository;

	@GetMapping("/shipments")
	public ResponseEntity<List<Shipment>> getAllShipments() {
		try {
			List<Shipment> shipments = new ArrayList<Shipment>();

			shipmentRepository.findAll().forEach(shipments::add);

			if (shipments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(shipments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/shipments/{tracking_no}")
	public ResponseEntity<Shipment> getShipmentById(@PathVariable("tracking_no") String tracking_no) {
		Optional<Shipment> shipmentData = shipmentRepository.findByTrackingNumber(tracking_no);

		if (shipmentData.isPresent()) {
			return new ResponseEntity<>(shipmentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/shipments")
	public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
		try {
			Shipment _shipment = shipmentRepository
					.save(new Shipment(shipment.getOrigin(), shipment.getDestination(), shipment.getCurrentStatus(), shipment.getTrackingNumber(), shipment.getCourierCode()));
			return new ResponseEntity<>(_shipment, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/shipments/{id}")
	public ResponseEntity<Shipment> updateShipment(@PathVariable("id") String id, @RequestBody Shipment shipment) {
		Optional<Shipment> shipmentData = shipmentRepository.findById(id);

		if (shipmentData.isPresent()) {
			Shipment _shipment = shipmentData.get();
			_shipment.setOrigin(shipment.getOrigin());
			_shipment.setDestination(shipment.getDestination());
			_shipment.setTrackingNumber(shipment.getTrackingNumber());
			_shipment.setCourierCode(shipment.getCourierCode());
			_shipment.setCurrentStatus(shipment.getCurrentStatus());
			return new ResponseEntity<>(shipmentRepository.save(_shipment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/shipments/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
		try {
			shipmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

