package com.dronefeeder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dronefeeder.service.VideoService;

@DisplayName("VideoControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {
  @MockBean
  VideoService service;

  @Autowired
  MockMvc mockMvc;

  private static final String PATH = "/video";

  @Test
  @DisplayName("Must be able to do POST of video")
  public void testPost() throws Exception {
    
  }
}
