package com.example.demo.service;

import com.example.demo.repository.DeliveryRepository;
import com.example.demo.exception.DeliveryNotFoundException;
import com.example.demo.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  public Delivery create(String latitude, String longitude) {
    LocalDateTime postedDate = LocalDateTime.now();

    Delivery delivery = new Delivery();
    delivery.setLatitude(latitude);
    delivery.setLongitude(longitude);
    delivery.setDeliveryStatus("In transit");
    delivery.setPostedDate(postedDate);
    return repository.save(delivery);
  }

  public List<Delivery> getAllDeliverys() {
    return repository.findAll();
  }

  public Delivery getDeliveryById(int id) {
    return repository.findById(id).orElse(null);
  }

  public Delivery finishDelivery(int id, String video) {
    Delivery delivery = repository.findById(id).orElse(null);
    LocalDateTime deliveredDate = LocalDateTime.now();
    if (delivery == null) {
      throw new DeliveryNotFoundException("Delivery not found");
    }
    delivery.setDeliveryStatus("Delivered");
    delivery.setDeliveredDate(deliveredDate);
    delivery.setVideo(video);
    return repository.save(delivery);
  }

  public void deleteDelivery(int id) {
    repository.deleteById(id);
  }
}
