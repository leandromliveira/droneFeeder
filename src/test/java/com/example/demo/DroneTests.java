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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Drone;
import com.example.demo.repository.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneTests {

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private DroneRepository droneRepository;

	@Captor
	private ArgumentCaptor<Drone> droneCaptor;

	@BeforeEach
	public void setup() {
		droneRepository.deleteAll();
	}
	
	@Test
	@Order(1)
  @DisplayName("1 -  Deve adicionar um Drone na base de dados.")
	void deveAdicionarDroneNaBaseDeDados() throws Exception {
		final var drone = new Drone();
		mockMvc
			.perform(post("/drone").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(drone)))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(droneRepository, atLeast(1)).save(droneCaptor.capture());

		assertThat(droneCaptor.getValue()).isNotNull();
    assertThat(droneCaptor.getValue().getId()).isNotNull();
		assertThat(droneCaptor.getValue().getLatitude()).isEqualTo("-15.9041343");
		assertThat(droneCaptor.getValue().getLongitude()).isEqualTo("-48.1299912");
    assertThat(droneCaptor.getValue().getDeliveries()).isNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2 - Deve retornar todas os drones existentes da base de dados.")
	void deveRetornarTodosDronesExistentesNaBase() throws Exception {
	    final var drone1 = new Drone();
	    final var drone2 = new Drone();
	    droneRepository.save(drone1);
	    droneRepository.save(drone2);

	    mockMvc.perform(get("/drone").contentType(MediaType.APPLICATION_JSON))
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].id").value(drone1.getId()))
	        .andExpect(jsonPath("$[1].id").value(drone2.getId()));
	  }
	
	@Test
	  @Order(3)
	  @DisplayName("3 - Deve remover drone, por um id existente informado.")
	  void deveRemoverDroneQuandoExistirNaBase() throws Exception {
	    final var drone = droneRepository.save(new Drone());

	    mockMvc.perform(delete("/drone/" + drone.getId())).andExpect(status().isOk());
	  }

	@Test
		@Order(4)
		@DisplayName("4 - Deve encontrar drone, por um id existente informado.")
		void deveEncontrarDroneQuandoExistirNaBase() throws Exception {
	    final var drone = new Drone();
			droneRepository.save(drone);

	    mockMvc.perform(get("/drone/" + drone.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("id").value(drone.getId()));
	  }
}
