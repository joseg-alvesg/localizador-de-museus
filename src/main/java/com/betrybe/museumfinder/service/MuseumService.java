package com.betrybe.museumfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;

@Service
public class MuseumService implements MuseumServiceInterface {

  MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getClosestMuseum'");
  }

  @Override
  public Museum createMuseum(Museum museum) {

    if (!isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException("Invalid coordinate");
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMuseum'");
  }

}
