package com.dronefeeder.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dronefeeder.model.Drone;
import com.dronefeeder.service.DroneService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@DisplayName("DroneControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
public class DroneControllerTest {
  @MockBean
  DroneService droneService;

  @Autowired
  private MockMvc mockMvc;

  private static final String PATH = "/drone";

  @Test
  @DisplayName("Must be able to do POST of drone")
  public void testPost() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(PATH)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    
    verify(droneService, times(1)).create();
  }

  @Test
  @DisplayName("must be return a list of drones")
  public void testGetAll() throws Exception {
    Drone drone = new Drone();
    List<Drone> drones = Collections.singletonList(drone);

    when(droneService.getAllDrones()).thenReturn(drones);

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();
    
    String jsonEsperado = 
        "[{\"id\":" + drone.getId() + ",\"latitude\":null,\"longitude\":null,\"available\":null}]";
    Assertions.assertEquals(jsonEsperado, response.getContentAsString());
  }

  @Test
  @DisplayName("must be return a determined drone")
  public void testGetDroneById() throws Exception {
    Drone drone = new Drone();

    when(droneService.getDroneById(drone.getId())).thenReturn(drone);

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
        .get(PATH + "/" + drone.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    String jsonEsperado = "{\"id\":0,\"latitude\":null,\"longitude\":null,\"available\":null}";
    Assertions.assertEquals(jsonEsperado, response.getContentAsString());
  }

  @Test
  @DisplayName("Must be able to Delete a drone")
  public void testDelete() throws Exception, NullPointerException {
    Drone drone = new Drone();
    mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + drone.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    
    verify(droneService, times(1)).delete(drone.getId());
  }
}
