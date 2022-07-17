package com.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  private String longitude;
  private String latitude;
  private LocalDateTime postedDate;
  private LocalDateTime deliveredDate;
  private String deliveryStatus;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "video_id")
  private Video video;

  public int getId() {
    return id;
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

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

}
