package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
	Optional<Shipment> findByTrackingNumber(String trackingNumber);
	//List<Shipment> findByCourierCode(String courierCode);
}
