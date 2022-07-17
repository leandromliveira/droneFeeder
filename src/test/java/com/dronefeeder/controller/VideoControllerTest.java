package com.dronefeeder.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.Video;
import com.dronefeeder.service.VideoService;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("VideoControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {
  @MockBean
  VideoService service;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  WebApplicationContext webApplicationContext;

  private static final String PATH = "/video";

  @Test
  @DisplayName("Must be able to do POST of video")
  public void testPost() throws Exception {
    Delivery delivery = new Delivery();
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes()
    );

    MockMvc mockMvc 
        = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    mockMvc.perform(multipart(PATH + "/" + delivery.getId()).file(file))
      .andExpect(status().isOk());

    verify(service, times(1)).create(delivery.getId(), file.getBytes());
  }

  @Test
  @DisplayName("must be return a list of urlVideos")
  public void testGetAll() throws Exception {
    String url = "http://localhost:8080/video/4";
    List<String> urls = Collections.singletonList(url);

    when(service.getAll()).thenReturn(urls);

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();
    
    String jsonEsperado = "[\"http://localhost:8080/video/4\"]";
    Assertions.assertEquals(jsonEsperado, response.getContentAsString());
  }

  @Test
  @DisplayName("must be return a determined video")
  public void testGetDroneById() throws Exception {
    Delivery delivery = new Delivery();
    MockMultipartFile file = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes()
    );
    Video video = new Video();
    video.setVideo(file.getBytes());
    delivery.setVideo(video);

    when(service.getFile(delivery.getId())).thenReturn(delivery.getVideo());

    mockMvc.perform(MockMvcRequestBuilders
        .get(PATH + "/" + delivery.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  
    verify(service, times(1)).getFile(video.getId());
    
  }
}
