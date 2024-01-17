package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste do Controller de Tipos de Coleções")
public class CollectionTypeControllerTest {

  @MockBean
  CollectionTypeService collectionTypeService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Deve retornar status 200 ao buscar a coleção especificada por nome")
  public void getCollectionTypesCountTest() throws Exception {
    String[] typeList = new String[] { "hist,art,filos" };
    String typeString = "hist, art, filos";
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typeList, 3);
    when(collectionTypeService.countByCollectionTypes(typeString)).thenReturn(collectionTypeCount);

    mockMvc.perform(get("/collections/count/" + typeString)
        .param("types", typeString)
        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(3));

    verify(collectionTypeService).countByCollectionTypes(typeString);
  }

  @Test
  @DisplayName("Deve retornar status 404 ao buscar a coleção especificada por nome")
  public void getCollectionTypesCountNotFoundTest() throws Exception {
    String[] typeList = new String[] { "hist,art,filos" };
    String typeString = "hist, art, filos";
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typeList, 0);
    when(collectionTypeService.countByCollectionTypes(typeString)).thenReturn(collectionTypeCount);

    mockMvc.perform(get("/collections/count/" + typeString)
        .param("types", typeString)
        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(collectionTypeService).countByCollectionTypes(typeString);
  }

}
