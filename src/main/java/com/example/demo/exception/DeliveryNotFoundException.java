package com.example.demo.exception;

public class DeliveryNotFoundException extends RuntimeException {
  public DeliveryNotFoundException(String message) {
    super(message);
  }
}