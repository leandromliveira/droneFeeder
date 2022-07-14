package com.dronefeeder.exception;

public class DataError {
  private String err;

  public DataError(String err) {
    this.err = err;
  }

  public String getError() {
    return err;
  }

  public void setError(String err) {
    this.err = err;
  }
}