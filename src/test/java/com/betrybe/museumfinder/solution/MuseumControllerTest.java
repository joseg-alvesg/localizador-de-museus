package com.betrybe.museumfinder.solution;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Museum Controller Test")
public class MuseumControllerTest {

  @MockBean
  MuseumService museumService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Deve retornar o status 200 quando buscar o museu pelo id")
  void getMusumTest() throws Exception {
    Long id = 1L;
    Museum museum = new Museum();
    museum.setId(1L);
    when(museumService.getMuseum(id)).thenReturn(museum);
    mockMvc
      .perform(get("/museums/{id}", "1")
      .param("id", "1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(id));
  }
}
