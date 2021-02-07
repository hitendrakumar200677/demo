package com.example.demo.model;

import javax.persistence.*;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "shipments")
public class Shipment {
	
	@Id
    @GenericGenerator(name = "shipment_id", strategy = "com.example.model.generator.ShipmentIdGenerator")
    @GeneratedValue(generator = "shipment_id")  
    @Column(name="shipment_id")
    public String shipmentId;


	@Column(name = "origin")
	private String origin;

	@Column(name = "destination")
	private String destination;
	
	@Column(name = "current_status")
	private String currentStatus;

	@Column(name = "tracking_no")
	private String trackingNumber;
	
	@Column(name = "courier_code")
	private String courierCode;

	public Shipment() {

	}

	public Shipment(String origin, String destination, String currentStatus, String trackingNumber, String courierCode) {
		this.origin = origin;
		this.destination = destination;
		this.currentStatus = currentStatus;
		this.trackingNumber = trackingNumber;
		this.courierCode = courierCode;
	}

	public String getShipmentId() {
		return shipmentId;
	}
	
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCourierCode() {
		return courierCode;
	}

	public void setCourierCode(String courierCode) {
		this.courierCode = courierCode;
	}

	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", origin=" + origin + ", destination=" + destination
				+ ", currentStatus=" + currentStatus + ", trackingNumber=" + trackingNumber + ", courierCode="
				+ courierCode + "]";
	}

}
