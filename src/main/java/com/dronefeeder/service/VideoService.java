package com.dronefeeder.service;

import com.dronefeeder.exception.DeliveryNotFoundException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.VideoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
  @Autowired
  private VideoRepository repository;

  @Autowired
  private DeliveryRepository deliveryRepository;

  /**
   * Method to create an video.
   */
  public Video create(int deliveryId,byte[] video) {
    Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);
    if (delivery == null) {
      throw new DeliveryNotFoundException("Delivery not found!");
    }
    Video v = new Video();
    v.setVideo(video);
    v.setDelivery(delivery);
    return repository.save(v);
  }

  public List<String> getAll() {
    return repository.findAll().stream().map(video -> "http://localhost:8080/video/" + video.getId())
        .collect(Collectors.toList());
  }

  public Video getFile(int id) {
    return repository.findById(id).orElse(null);
  }
}
