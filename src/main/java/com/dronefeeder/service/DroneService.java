package com.dronefeeder.service;

import com.dronefeeder.model.Drone;
import com.dronefeeder.repository.DroneRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
  @Autowired
  private DroneRepository repository;

  /**
   * Method to create an drone.
   */
  public Drone create() {
    Drone newDrone = new Drone();
    newDrone.setLatitude("-15.9041343");
    newDrone.setLongitude("-48.1299912");
    newDrone.setAvailable(true);
    return repository.save(newDrone);
  }

  public List<Drone> getAllDrones() {
    return repository.findAll();
  }

  public Drone getDroneById(int id) {
    return repository.findById(id).orElse(null);
  }

  public void delete(int id) {
    repository.deleteById(id);
  }

}
