package com.betrybe.museumfinder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/museums")
public class MusumController {

  MuseumServiceInterface museumService;

  @Autowired
  public MusumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum createdMuseum = museumService.createMuseum(museum);
    return ResponseEntity.created(null).body(createdMuseum);
  }

  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(@RequestParam String lat,
      String lng,
      String max_dist_km) {
    Coordinate coordinate = new Coordinate(
        Double.parseDouble(lat),
        Double.parseDouble(lng));
    Museum museum = museumService.getClosestMuseum(coordinate, Double.parseDouble(max_dist_km));
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok(museumDto);
  }
}
