package com.betrybe.museumfinder.exception;

/**
 * this class represents a museum not found exception.
 */
public class MuseumNotFoundException extends RuntimeException {
  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }
}
