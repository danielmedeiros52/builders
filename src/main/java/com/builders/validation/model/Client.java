package com.builders.validation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

@ApiModel(description = "Can be sent any attribute to patch", value = "Client to patch")
public class Client {

  @Id
  @ApiModelProperty(hidden = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ApiModelProperty(hidden = true)
  @JsonProperty("nome")
  @Column(name = "name")
  private String name;

  @ApiModelProperty(hidden = true)
  @Column(name = "cpf")
  private String cpf;

  @ApiModelProperty(hidden = true)
  @JsonFormat(pattern = "dd/MM/yyyy")
  @JsonProperty("data_nascimento")
  @Column(name = "born_at")
  private LocalDate bornDate;

  @ApiModelProperty(hidden = true)
  @Column(name = "created_at")
  private LocalDateTime creationAt;

  @ApiModelProperty(hidden = true)
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
