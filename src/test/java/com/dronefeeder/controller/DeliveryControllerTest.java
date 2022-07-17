package com.dronefeeder.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dronefeeder.dto.DeliveryDto;
import com.dronefeeder.dto.FinishDeliveryDto;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@DisplayName("DeliveryControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryControllerTest {
  @MockBean
  DeliveryService deliveryService;

  @Autowired
  private MockMvc mockMvc;

  private static final String PATH = "/delivery";

  @Test
  @DisplayName("Must be able to do POST of delivery")
  public void testPost() throws Exception {
    DeliveryDto dto = new DeliveryDto();

    String json = new ObjectMapper().writeValueAsString(dto);
    mockMvc.perform(MockMvcRequestBuilders.post(PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());

    verify(deliveryService, times(1)).create(dto.getLatitude(), dto.getLongitude());
  }

  @Test
  @DisplayName("must be return a list of deliveries")
  public void testGetAll() throws Exception {
    Delivery delivery = new Delivery();
    List<Delivery> deliveries = Collections.singletonList(delivery);

    when(deliveryService.getAllDeliverys()).thenReturn(deliveries);

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();
    
    String jsonEsperado = 
        "[{\"id\":0,\"drone\":null,\"longitude\":null,\"latitude\":null," 
        + "\"postedDate\":null,\"deliveredDate\":null,\"deliveryStatus\":null,\"video\":null}]";
    Assertions.assertEquals(jsonEsperado, response.getContentAsString());
  }

  @Test
  @DisplayName("must be return a determined delivery")
  public void testGetDeliveryById() throws Exception {
    Delivery delivery = new Delivery();

    when(deliveryService.getDeliveryById(delivery.getId())).thenReturn(delivery);

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
        .get(PATH + "/" + delivery.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    String jsonEsperado = "{\"id\":0,\"drone\":null,\"longitude\":null,"
        + "\"latitude\":null,\"postedDate\":null,\"deliveredDate\":null,"
        + "\"deliveryStatus\":null,\"video\":null}";
    Assertions.assertEquals(jsonEsperado, response.getContentAsString());
  }

  @Test
  @DisplayName("Must be able to Delete a delivery")
  public void testDelete() throws Exception, NullPointerException {
    Delivery delivery = new Delivery();
    mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + delivery.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    
    verify(deliveryService, times(1)).deleteDelivery(delivery.getId());
  }

  @Test
  @DisplayName("Must be able to Finish a delivery")
  public void testFinish() throws Exception {
    FinishDeliveryDto dto = new FinishDeliveryDto();
    Delivery delivery = new Delivery();

    String json = new ObjectMapper().writeValueAsString(dto);
    mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + delivery.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());

    verify(deliveryService, times(1)).finishDelivery(delivery.getId(), dto.getVideoId());
  }

}
