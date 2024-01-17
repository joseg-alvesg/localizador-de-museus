package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Museum Service Test")
public class MuseumServiceTest {
  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  MuseumService museumService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Deve retornar um objeto do tipo museu quando buscar pelo id")
  void getMusumTest() throws Exception {
    Long id = 1L;
    Museum museum = new Museum();
    museum.setId(1L);
    when(museumFakeDatabase.getMuseum(id)).thenReturn(Optional.of(museum));
    Museum museumResponse = museumService.getMuseum(id);
    assertEquals(museumResponse.getId(), id);
  }

  @Test
  @DisplayName("Deve retornar uma exceção quando buscar pelo id e não encontrar o museu")
  void getMusumTestException() throws Exception {
    Long id = 1L;
    when(museumFakeDatabase.getMuseum(id)).thenReturn(Optional.empty());
    try {
      museumService.getMuseum(id);
    } catch (Exception e) {
      assertEquals(e.getClass().getSimpleName(), "MuseumNotFoundException");
    }
  }

}
