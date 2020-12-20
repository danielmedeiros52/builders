package com.builders.validation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("clientDto")
@ApiModel(description = "Model expected of Client", value = "Cliente")
public class ClientDto {

  @ApiModelProperty(hidden = true, position = 1)
  @JsonProperty("id")
  private int id;

  @NotBlank
  @JsonProperty("nome")
  @ApiModelProperty(required = true, example = "Joe Doe", position = 2)
  private String name;

  @NotBlank
  @JsonProperty("cpf")
  @ApiModelProperty(required = true, example = "12345667890", position = 3)
  private String cpf;

  @ApiModelProperty(hidden = true)
  @JsonProperty("idade")
  private int age;

  @NotNull
  @ApiModelProperty(required = true, example = "01/01/2001", position = 4)
  @JsonFormat(pattern = "dd/MM/yyyy")
  @JsonProperty("data_nascimento")
  private LocalDate bornDate;

  @JsonProperty("criado_em")
  @ApiModelProperty(hidden = true)
  private String creationAt;

  @JsonInclude(Include.NON_EMPTY)
  @ApiModelProperty(hidden = true)
  @JsonProperty("atualizado_em")
  private String updatedAt;


  public int getAge() {
    return LocalDate.now().getYear() - this.bornDate.getYear();
  }
}
