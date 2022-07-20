package com.dronefeeder.exception;

public class DroneNotAvailableException extends RuntimeException {
  public DroneNotAvailableException(String message) {
    super(message);
  }
}