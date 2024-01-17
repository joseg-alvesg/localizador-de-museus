package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste da service CollectionTypeService")
class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  CollectionTypeService collectionTypeService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Teste do método countByCollectionTypes")
  void testCountByCollectionTypes() {
    String typesList = "hist,art,filos";

    when(museumFakeDatabase.countByCollectionType("hist")).thenReturn(1L);
    when(museumFakeDatabase.countByCollectionType("art")).thenReturn(2L);
    when(museumFakeDatabase.countByCollectionType("filos")).thenReturn(0L);

    CollectionTypeCount result = collectionTypeService.countByCollectionTypes(typesList);

    assertEquals(3, result.count());
  }

  @Test
  @DisplayName("Teste do método countByCollectionTypes")
  void testCountByCollectionTypesEmpty() {
    String typesList = "hist,art,filos";

    when(museumFakeDatabase.countByCollectionType("hist")).thenReturn(0L);

    CollectionTypeCount result = collectionTypeService.countByCollectionTypes(typesList);

    assertEquals(0, result.count());
  }
}
