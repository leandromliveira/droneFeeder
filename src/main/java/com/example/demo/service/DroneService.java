package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Drone;
import com.example.demo.repository.DroneRepository;

@Service
public class DroneService {
  @Autowired
  private DroneRepository repository;

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
