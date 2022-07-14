package com.dronefeeder.controller;

import com.dronefeeder.exception.DataError;
import com.dronefeeder.exception.DeliveryNotFoundException;
import com.dronefeeder.exception.DroneNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GerenciadorAdvice {
  @ExceptionHandler({DeliveryNotFoundException.class})
  public ResponseEntity<DataError> handlerNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(new DataError(exception.getMessage()));
  }

  @ExceptionHandler({DroneNotAvailableException.class})
  public ResponseEntity<DataError> handlerConflict(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
      .body(new DataError(exception.getMessage()));
  }
}
