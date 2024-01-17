package com.betrybe.museumfinder.exception;

/**
 * this class represents a invalid coordinate exception.
 */
public class InvalidCoordinateException extends RuntimeException {
  public InvalidCoordinateException() {
    super("Coordenada inválida!");
  }
}
