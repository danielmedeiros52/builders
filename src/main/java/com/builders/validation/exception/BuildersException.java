package com.builders.validation.exception;

import com.builders.validation.utils.SystemErrorMessages;
import lombok.Data;

@Data
public class BuildersException extends Exception {

  private String flow;
  private int statusCode;

  public BuildersException(SystemErrorMessages systemErrorMessages) {
    super(systemErrorMessages.getMessage());
    this.flow = systemErrorMessages.getCause();
    this.statusCode = systemErrorMessages.getStatusCode();
  }
}
