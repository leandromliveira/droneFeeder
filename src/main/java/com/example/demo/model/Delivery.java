package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  private String longitude;
  private String latitude;
  private LocalDateTime postedDate;
  private LocalDateTime deliveredDate;
  private String deliveryStatus;
  private String video;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public LocalDateTime getPostedDate() {
    return postedDate;
  }

  public void setPostedDate(LocalDateTime postedDate) {
    this.postedDate = postedDate;
  }

  public LocalDateTime getDeliveredDate() {
    return deliveredDate;
  }

  public void setDeliveredDate(LocalDateTime deliveredDate) {
    this.deliveredDate = deliveredDate;
  }

  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(String deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

}
