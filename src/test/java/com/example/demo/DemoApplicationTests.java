package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
 
import com.example.demo.model.Shipment;
import com.example.demo.controller.ShipmentController;
import com.example.demo.repository.ShipmentRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class DemoApplicationTests {

	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private ShipmentRepository shipmentRepository;
 
    private static ObjectMapper mapper = new ObjectMapper();
 
    @Test
    public void testGetExample() throws Exception {
        List<Shipment> shipments = new ArrayList<>();
        
        Shipment shipment = new Shipment();
        shipment.setOrigin("DELHI");
        shipment.setDestination("MUMBAI");
        shipment.setCourierCode("FEDEX");
        shipment.setCurrentStatus("IN-TRANSIT");
        shipments.add(shipment);
        
        shipment = new Shipment();
        shipment.setOrigin("JSR");
        shipment.setDestination("JAIPUR");
        shipment.setCourierCode("FEDEX");
        shipment.setCurrentStatus("IN-TRANSIT");
        shipments.add(shipment);
        
        shipment = new Shipment();
        shipment.setOrigin("NOIDA");
        shipment.setDestination("GURGAON");
        shipment.setCourierCode("FEDEX");
        shipment.setCurrentStatus("IN-TRANSIT");
        shipments.add(shipment);
        
        Mockito.when(shipmentRepository.findAll()).thenReturn(shipments);
        mockMvc.perform(get("/api/shipments")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].origin", Matchers.equalTo("NOIDA")));
    }
 
    @Test
    public void testPostExample() throws Exception {
        Shipment shipment = new Shipment();
        
        shipment.setOrigin("DELHI");
        shipment.setDestination("MUMBAI");
        shipment.setCurrentStatus("IN-TRANSIT");
        shipment.setTrackingNumber("342345345436");
        shipment.setCourierCode("FEDEX");
                
        Mockito.when(shipmentRepository.save(ArgumentMatchers.any())).thenReturn(shipment);
        String json = mapper.writeValueAsString(shipment);
        mockMvc.perform(post("/api/shipments").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.origin", Matchers.equalTo("DELHI")))
                .andExpect(jsonPath("$.destination", Matchers.equalTo("MUMBAI")))
                .andExpect(jsonPath("$.currentStatus", Matchers.equalTo("IN-TRANSIT")))
                .andExpect(jsonPath("$.trackingNumber", Matchers.equalTo("342345345436")))
                .andExpect(jsonPath("$.courierCode", Matchers.equalTo("FEDEX")));
    }
     

}
