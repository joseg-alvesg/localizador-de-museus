package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * this class is responsible for handling requests related to museums.
 */
@RestController
@RequestMapping("/museums")
public class MusumController {

  MuseumServiceInterface museumService;

  /**
   * constructor.
   *
   * @param museumService the service that will be used to handle the requests.
   */
  @Autowired
  public MusumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * this method is responsible for handling the request to get a museum by id.
   *
   * @param museumCreationDto the museum that will be created.
   * @return the created museum.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum createdMuseum = museumService.createMuseum(museum);
    return ResponseEntity.created(null).body(createdMuseum);
  }

  /**
   * this method is responsible for handling the request to get a museum by id.
   *
   * @param lat the latitude of the coordinate.
   * @param lng the longitude of the coordinate.
   * @param maxDistKm the maximum distance in kilometers.
   * @return the closest museum.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(@RequestParam String lat,
      String lng,
      @RequestParam(name = "max_dist_km") String maxDistKm) {
    Coordinate coordinate = new Coordinate(
        Double.parseDouble(lat),
        Double.parseDouble(lng));
    Museum museum = museumService.getClosestMuseum(coordinate, Double.parseDouble(maxDistKm));
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok(museumDto);
  }
}
