package com.builders.validation.exception;

import com.builders.validation.utils.SystemErrorMessages;

public class ClientException extends BuildersException {

  public ClientException(SystemErrorMessages systemErrorMessages) {
    super(systemErrorMessages);
  }
}
