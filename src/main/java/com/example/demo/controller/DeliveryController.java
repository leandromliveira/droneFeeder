package com.example.demo.controller;

import com.example.demo.service.DeliveryService;
import com.example.demo.dto.DeliveryDto;
import com.example.demo.dto.FinishDeliveryDto;
import com.example.demo.model.Delivery;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
  @Autowired
  private DeliveryService service;

  @PostMapping
  public Delivery create(@RequestBody DeliveryDto dto) {
    return service.create(dto.getLatitude(), dto.getLongitude());

  }

  @GetMapping
  public List<Delivery> getAllDeliverys() {
    return service.getAllDeliverys();
  }

  @GetMapping("/{id}")
  public Delivery getDeliveryById(@PathVariable("id") int id) {
    return service.getDeliveryById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") int id) {
    service.deleteDelivery(id);
  }

  @PutMapping("/{id}")
  public Delivery finishDelivery(@PathVariable("id") int id, @RequestBody FinishDeliveryDto dto) {
    return service.finishDelivery(id, dto.getVideo());
  }
}