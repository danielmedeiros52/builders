package com.builders.validation.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemErrorMessages {
  CLIENT_NOT_FOUND("Nao foi encontrato nenhum cliente com o id informado.", "client_not_found", 404);

  private final String message;
  private final String cause;
  private final int statusCode;


}
