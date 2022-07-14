package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.DeliveryDto;
import com.example.demo.model.Delivery;
import com.example.demo.model.Drone;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryTests {
  @Autowired
	private MockMvc mockMvc;

	@Autowired
	private DroneRepository droneRepository;

  @SpyBean
  private DeliveryRepository deliveryRepository;

	@Captor
	private ArgumentCaptor<Delivery> deliveryCaptor;

	@BeforeEach
	public void setup() {
		droneRepository.deleteAll();
    deliveryRepository.deleteAll();
	}
	
	@Test
	@Order(1)
  @DisplayName("1 -  Deve adicionar uma Entrega na base de dados.")
	void deveAdicionarEntregaNaBaseDeDados() throws Exception {
		final var drone = new Drone();
    mockMvc
			.perform(post("/drone").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(drone)))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    final var delivery = new Delivery();
    delivery.setLatitude("15.13");
    delivery.setLongitude("15.45");

    System.out.println(mockMvc
    .perform(post("/delivery").contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(delivery))));

		mockMvc
			.perform(post("/delivery").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(delivery)))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(415));

		verify(deliveryRepository, atLeast(1)).save(deliveryCaptor.capture());

		assertThat(deliveryCaptor.getValue()).isNotNull();
    assertThat(deliveryCaptor.getValue().getId()).isNotNull();
		assertThat(deliveryCaptor.getValue().getLatitude()).isEqualTo("-15.9041343");
		assertThat(deliveryCaptor.getValue().getLongitude()).isEqualTo("-48.1299912");
	
    assertThat(deliveryCaptor.getValue().getPostedDate()).isEqualTo(delivery.getPostedDate());
    assertThat(deliveryCaptor.getValue().getDeliveredDate()).isNull();
    assertThat(deliveryCaptor.getValue().getDeliveryStatus()).isEqualTo(delivery.getDeliveryStatus());
    assertThat(deliveryCaptor.getValue().getVideo()).isEqualTo(delivery.getVideo());
    assertThat(deliveryCaptor.getValue().getDrone()).isEqualTo(drone.getId());


	}
	
	// @Test
	// @Order(2)
	// @DisplayName("2 - Deve retornar todas os drones existentes da base de dados.")
	// void deveRetornarTodosDronesExistentesNaBase() throws Exception {
	//     final var drone1 = new Drone();
	//     final var drone2 = new Drone();
	//     droneRepository.save(drone1);
	//     droneRepository.save(drone2);

	//     mockMvc.perform(get("/drone").contentType(MediaType.APPLICATION_JSON))
	//         .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	//         .andExpect(jsonPath("$[0].id").value(drone1.getId()))
	//         .andExpect(jsonPath("$[1].id").value(drone2.getId()));
	//   }
	
	// @Test
	//   @Order(3)
	//   @DisplayName("3 - Deve remover drone, por um id existente informado.")
	//   void deveRemoverDroneQuandoExistirNaBase() throws Exception {
	//     final var drone = droneRepository.save(new Drone());

	//     mockMvc.perform(delete("/drone/" + drone.getId())).andExpect(status().isOk());
	//   }

	// @Test
	// 	@Order(4)
	// 	@DisplayName("4 - Deve encontrar drone, por um id existente informado.")
	// 	void deveEncontrarDroneQuandoExistirNaBase() throws Exception {
	//     final var drone = new Drone();
	// 		droneRepository.save(drone);

	//     mockMvc.perform(get("/drone/" + drone.getId())).andExpect(status().isOk())
	// 			.andExpect(jsonPath("id").value(drone.getId()));
	//   }
}
