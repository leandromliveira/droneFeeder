package com.dronefeeder.controller;

import com.dronefeeder.dto.FinishDeliveryDto;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.service.DeliveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/delivery",
    consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryController {
  @Autowired
  private DeliveryService service;

  @PostMapping
  public Delivery create(@RequestBody Delivery dto) {
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