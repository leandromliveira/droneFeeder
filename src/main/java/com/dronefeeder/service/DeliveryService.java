package com.dronefeeder.service;

import com.dronefeeder.exception.DeliveryNotFoundException;
import com.dronefeeder.exception.DroneNotAvailableException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.DroneRepository;
import com.dronefeeder.repository.VideoRepository;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
  @Autowired
  private DeliveryRepository repository;

  @Autowired
  private DroneRepository droneRepository;

  @Autowired
  private VideoRepository videoRepository;

  /**
   * Methdo to create an Delivery.
   */
  @Transactional
  public Delivery create(String latitude, String longitude) {
    LocalDateTime postedDate;
    postedDate = LocalDateTime.now();
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
    return repository.save(delivery);
  }

  public List<Delivery> getAllDeliverys() {
    return repository.findAll();
  }

  public Delivery getDeliveryById(int id) {
    return repository.findById(id).orElse(null);
  }

  /**
   * Method do finishDelivery.
   */
  @Transactional
  public Delivery finishDelivery(int id, int videoId) {
    Delivery delivery = repository.findById(id).orElse(null);
    LocalDateTime deliveredDate; 
    Video video; 
    video = videoRepository.findById(videoId).orElse(null);
    deliveredDate = LocalDateTime.now();
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
