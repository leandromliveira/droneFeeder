package com.dronefeeder.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.dronefeeder.exception.DeliveryNotFoundException;
import com.dronefeeder.exception.DroneNotAvailableException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.DroneRepository;
import com.dronefeeder.repository.VideoRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@DisplayName("DeliveryServiceTest")
@SpringBootTest
@AutoConfiguration
public class DeliveryServiceTest {
  @MockBean
  private DeliveryRepository repository;

  @MockBean
  private DroneRepository droneRepository;

  @MockBean
  private VideoRepository videoRepository;

  @Autowired
  private DeliveryService service;

  @Test
  @DisplayName("must be able to create a delivery")
  public void createDelivery() throws Exception {
    Delivery delivery = new Delivery();
    List<Delivery> deliveries; 
    deliveries = Collections.singletonList(delivery);
    Drone drone = new Drone();
    drone.setAvailable(true);
    drone.addDelivery(delivery);
    List<Drone> drones = Collections.singletonList(drone);

    Mockito.when(droneRepository.findAll()).thenReturn(drones);
    
    Mockito.when(repository.save(Mockito.any(Delivery.class))).thenReturn(delivery);

  
    Assertions.assertEquals(delivery, service
        .create(delivery.getLatitude(), delivery.getLongitude()));

    Assertions.assertEquals(deliveries, droneRepository.findAll().get(0).getDeliveries());
  }

  @Test
  @DisplayName("must be able to get all deliveries")
  public void getAllDeliveries() throws Exception {
    Delivery delivery = new Delivery();
    List<Delivery> deliveries = Collections.singletonList(delivery);

    Mockito.when(repository.findAll()).thenReturn(deliveries);

    Assertions.assertEquals(deliveries, service.getAllDeliverys());
  }

  @Test
  @DisplayName("must be able to get a delivery by specific Id")
  public void getDeliveryById() throws Exception {
    Delivery delivery = new Delivery();
    Optional<Delivery> opDelivery = Optional.of(delivery);

    Mockito.when(repository.findById(delivery.getId())).thenReturn(opDelivery);

    Assertions.assertEquals(delivery, service.getDeliveryById(delivery.getId()));
  }

  @Test
  @DisplayName("must be able to delete a delivery")
  public void deleteDelivery() throws Exception {
    Delivery delivery = new Delivery();

    service.deleteDelivery(delivery.getId());

    verify(repository, times(1)).deleteById(delivery.getId());
  }

  @Test
  @DisplayName("must be able to finish a delivery")
  public void finishDelivery() throws Exception {
    Delivery delivery = new Delivery();
    Drone drone = new Drone();
    delivery.setDrone(drone);
    Optional<Delivery> opDelivery = Optional.of(delivery);

    Video video = new Video();
    Optional<Video> opVideo = Optional.of(video);

    Mockito.when(repository.findById(delivery.getId())).thenReturn(opDelivery);

    Mockito.when(videoRepository.findById(delivery.getId())).thenReturn(opVideo);

    service.finishDelivery(delivery.getId(), video.getId());
  }

  @Test
  @DisplayName("should throw an exception because it is not possible to create a delivery without a drone available")
  public void throwExceptionInCreateDelivery() throws Exception {
    Delivery delivery = new Delivery();
    Drone drone = new Drone();
    drone.setAvailable(false);
    List<Drone> drones = Collections.singletonList(drone);

    Mockito.when(droneRepository.findAll()).thenReturn(drones);
    
    Mockito.when(repository.save(Mockito.any(Delivery.class))).thenReturn(delivery);

  
    Assertions.assertThrows(DroneNotAvailableException.
        class, () -> service
        .create(delivery.getLatitude(), delivery.getLongitude()));
  }

  @Test
  @DisplayName("should throw an exception because it is not possible to finalize a delivery not found")
  public void throwExceptionInFinishDelivery() throws Exception {
    Delivery delivery = new Delivery();
    Drone drone = new Drone();
    delivery.setDrone(drone);

    Video video = new Video();
    Optional<Video> opVideo = Optional.of(video);

    Mockito.when(videoRepository.findById(delivery.getId())).thenReturn(opVideo);

    Assertions.assertThrows(DeliveryNotFoundException.
        class, () -> service.finishDelivery(0, video.getId()));
  }

  @Test
  @DisplayName("should throw an exception if the delivery is not found")
  public void throwExceptionVideoNotFound() throws IOException {
    Integer nonExistentId = 404;
    Mockito.when(repository.findById(nonExistentId)).thenReturn(Optional.empty());

    Assertions.assertThrows(DeliveryNotFoundException.class, () -> service.getDeliveryById(nonExistentId));
  }
}
