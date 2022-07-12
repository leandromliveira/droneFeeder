package com.example.demo.exception;

public class DroneNotAvailableException extends RuntimeException {
  public DroneNotAvailableException(String message) {
    super(message);
  }
}