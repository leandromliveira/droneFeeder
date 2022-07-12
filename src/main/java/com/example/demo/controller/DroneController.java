package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Drone;
import com.example.demo.service.DroneService;

@RestController
public class DroneController {
  @Autowired
  DroneService droneService;

  @PostMapping("/drone")
  public Drone create() {
    return droneService.create();
  }

  @GetMapping("/drone")
  public List<Drone> getAllDrones() {
    return droneService.getAllDrones();
  }

  @GetMapping("/drone/{id}")
  public Drone getDrone(@PathVariable("id") int id) {
    return droneService.getDroneById(id);
  }

  @DeleteMapping("/drone/{id}")
  public void delete(@PathVariable("id") int id) {
    droneService.delete(id);
  }
}
