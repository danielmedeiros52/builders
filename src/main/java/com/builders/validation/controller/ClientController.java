package com.builders.validation.controller;

import com.builders.validation.dto.ClientDto;
import com.builders.validation.exception.ClientException;
import com.builders.validation.exception.ControllerExceptionHandler;
import com.builders.validation.service.ClientService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/client")
public class ClientController {

  private final ClientService clientService;

  @GetMapping
  public ResponseEntity<Page<ClientDto>> getClients(
      @PageableDefault(sort = "id", direction = Direction.ASC) Pageable page,
      @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
      @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf
  ) {
    return ResponseEntity.ok().body(clientService.getClients(page, nome, cpf));
  }

  @PostMapping
  public ResponseEntity<ClientDto> saveNewClient(@Valid @RequestBody ClientDto clientDto) {
    return ResponseEntity.ok().body(clientService.saveNewClient(clientDto));
  }

  @PutMapping
  public ResponseEntity updateClient(@Valid @RequestBody ClientDto clientDto) {
    try {
      return ResponseEntity.ok().body(clientService.updateClient(clientDto));
    } catch (ClientException e) {
      return ControllerExceptionHandler.customizeErrors(e);
    }
  }

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
