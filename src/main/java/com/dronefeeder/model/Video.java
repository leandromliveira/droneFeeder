package com.dronefeeder.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Lob
  private byte[] video;

  @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true, 
      fetch = FetchType.LAZY)
  private Delivery delivery;

  public int getId() {
    return id;
  }

  public byte[] getVideo() {
    return video;
  }

  public void setVideo(byte[] video) {
    this.video = video;
  }

  public Delivery getDelivery() {
    return this.delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }

}
