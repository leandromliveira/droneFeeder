package com.dronefeeder.service;

import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.VideoRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.buf.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;


@DisplayName("VideoServiceTest")
@SpringBootTest
@AutoConfiguration
public class VideoServiceTest {
  @MockBean
  private VideoRepository repository;

  @MockBean
  private DeliveryRepository deliveryRepository;

  @Autowired
  private VideoService service;

  @Test
  @DisplayName("must be able to save a video")
  public void create() throws IOException {
    Delivery delivery = new Delivery();
    Optional<Delivery> opDelivery = Optional.of(delivery);
    Video video = new Video();
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes()
    );
    video.setVideo(file.getBytes());

    Mockito.when(repository.save(Mockito.any(Video.class))).thenReturn(video);

    Mockito.when(deliveryRepository.findById(delivery.getId())).thenReturn(opDelivery);

    Assertions.assertEquals(video, service.create(delivery.getId(), video.getVideo()));
  }

  @Test
  @DisplayName("must be able to get all url videos")
  public void getAllVideos() throws Exception {
    Video video = new Video();
    List<Video> videos = Collections.singletonList(video);

    Mockito.when(repository.findAll()).thenReturn(videos);

    String json = "http://localhost:8080/video/" + video.getId();

    Assertions.assertEquals(json, StringUtils.join(service.getAll()));
  }

  @Test
  @DisplayName("must be able to get a determined video")
  public void getVideoById() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes()
    );
    Video video = new Video();
    video.setVideo(file.getBytes());
    Optional<Video> opVideo = Optional.of(video);

    Mockito.when(repository.findById(video.getId())).thenReturn(opVideo);

    Assertions.assertEquals(video, service.getFile(video.getId()));
  }
}
