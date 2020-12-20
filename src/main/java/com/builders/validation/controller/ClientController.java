package com.builders.validation.controller;

import com.builders.validation.config.patch.json.Patch;
import com.builders.validation.config.patch.json.PatchRequestBody;
import com.builders.validation.dto.ClientDto;
import com.builders.validation.exception.ClientException;
import com.builders.validation.exception.ControllerExceptionHandler;
import com.builders.validation.model.Client;
import com.builders.validation.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags = "Client Controller")
@RequestMapping(value = "/client")
public class ClientController {

  private final ClientService clientService;

  @ApiOperation(value = "Get clients, can be filtered through name or cpf or both as you prefer.")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ClientDto>> getClients(
      @PageableDefault(sort = "id", direction = Direction.ASC) Pageable page,
      @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
      @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf
  ) {
    return ResponseEntity.ok().body(clientService.getClients(page, nome, cpf));
  }

  @ApiOperation(value = "Insert a new client.")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({@ApiImplicitParam(name = "Cliente",
      value = "Data to save a new client.", paramType = "body", dataType = "Cliente")})
  public ResponseEntity<ClientDto> saveNewClient(@Valid @RequestBody ClientDto clientDto) {
    return ResponseEntity.ok().body(clientService.saveNewClient(clientDto));
  }

  @ApiOperation(value = "Update all fields on client.")
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({@ApiImplicitParam(name = "Cliente",
      value = "Data to update from the client.", paramType = "body", dataType = "Cliente")})
  public ResponseEntity updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable("id") int id) {
    try {
      return ResponseEntity.ok().body(clientService.updateClient(clientDto, id));
    } catch (ClientException e) {
      return ControllerExceptionHandler.customizeErrors(e);
    }
  }

  @ApiOperation(value = "Simple patch of client, possible send only one field to update.")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS")})
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({@ApiImplicitParam(name = "Cliente",
      value = "Can be sent only one attribute.", paramType = "body", dataType = "Cliente")})
  @Patch(service = ClientService.class, id = Integer.class)
  public ClientDto patch(@PathVariable int id,
      @ApiParam(name = "message", value = "Message to send", required = true) @PatchRequestBody Client client) {
    return clientService.patch(client, id);
  }

  @ApiOperation(value = "Delete a client.")
  @DeleteMapping("/{id}")
  public ResponseEntity deleteClient(@PathVariable("id") int id) {
    try {
      clientService.deleteClientById(id);
    } catch (ClientException e) {
      return ControllerExceptionHandler.customizeErrors(e);
    }
    return ResponseEntity.ok().build();
  }

}
