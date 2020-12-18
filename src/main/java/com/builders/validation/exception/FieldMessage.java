package com.builders.validation.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage implements Serializable {

    @JsonProperty("cause")
    private String fieldName;
    @JsonProperty("description")
    private String fieldErrorMessage;
}
