package com.example.demo.service;

import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.DroneRepository;
import com.example.demo.exception.DeliveryNotFoundException;
import com.example.demo.exception.DroneNotAvailableException;
import com.example.demo.model.Delivery;
import com.example.demo.model.Drone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  @Autowired
  private DroneRepository droneRepository;

  @Transactional
  public Delivery create(String latitude, String longitude) {
    LocalDateTime postedDate = LocalDateTime.now();
    List<Drone> droneList = droneRepository.findAll();
    Drone drone = droneList.stream().filter(d -> d.isAvailable() == true).findFirst().orElse(null);
    if (drone == null) {
      throw new DroneNotAvailableException("No available drone found");
    }
    drone.setAvailable(false);

    Delivery delivery = new Delivery();
    delivery.setLatitude(latitude);
    delivery.setLongitude(longitude);
    delivery.setDeliveryStatus("In transit");
    delivery.setPostedDate(postedDate);
    delivery.setDrone(drone);
    // drone.addDelivery(delivery);
    return repository.save(delivery);
  }

  public List<Delivery> getAllDeliverys() {
    return repository.findAll();
  }

  public Delivery getDeliveryById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Transactional
  public Delivery finishDelivery(int id, String video) {
    Delivery delivery = repository.findById(id).orElse(null);
    LocalDateTime deliveredDate = LocalDateTime.now();
    if (delivery == null) {
      throw new DeliveryNotFoundException("Delivery not found");
    }
    Drone drone = delivery.getDrone();
    drone.setAvailable(true);
    droneRepository.save(drone);
    delivery.setDeliveryStatus("Delivered");
    delivery.setDeliveredDate(deliveredDate);
    delivery.setVideo(video);
    return repository.save(delivery);
  }

  public void deleteDelivery(int id) {
    repository.deleteById(id);
  }
}
