package com.dronefeeder.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.dronefeeder.model.Drone;
import com.dronefeeder.repository.DroneRepository;

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

@DisplayName("DroneServiceTest")
@SpringBootTest
@AutoConfiguration
public class DroneServiceTest {
  @MockBean
  private DroneRepository repository;

  @Autowired
  private DroneService service;

  @Test
  @DisplayName("must be able to create a drone")
  public void createDrone() throws Exception {
    Drone drone = new Drone();
    drone.setLatitude("-15.9041343");
    drone.setLongitude("-48.1299912");
    drone.setAvailable(true);
    Mockito.when(repository.save(Mockito.any(Drone.class))).thenReturn(drone);
  
    Assertions.assertEquals(drone, service.create());
  }

  @Test
  @DisplayName("must be able to get all drones")
  public void getAllDrone() throws Exception {
    Drone drone = new Drone();
    List<Drone> drones = Collections.singletonList(drone);

    Mockito.when(repository.findAll()).thenReturn(drones);

    Assertions.assertEquals(drones, service.getAllDrones());
  }

  @Test
  @DisplayName("must be able to get a drone by specific Id")
  public void getDroneById() throws Exception {
    Drone drone = new Drone();
    Optional<Drone> opDrone = Optional.of(drone);

    Mockito.when(repository.findById(drone.getId())).thenReturn(opDrone);

    Assertions.assertEquals(drone, service.getDroneById(drone.getId()));
  }

  @Test
  @DisplayName("must be able to delete a drone")
  public void deleteDrone() throws Exception {
    Drone drone = new Drone();

    service.delete(drone.getId());

    verify(repository, times(1)).deleteById(drone.getId());
  }
}
