package com.builders.validation.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError implements Serializable {

    private Integer status;
    private String errorMessage;
    private Long instantOfError;
}
