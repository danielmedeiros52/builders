package com.builders.validation.service;

import com.builders.validation.dao.ClientDao;
import com.builders.validation.dto.ClientDto;
import com.builders.validation.exception.ClientException;
import com.builders.validation.model.Client;
import com.builders.validation.model.Client.ClientBuilder;
import com.builders.validation.utils.SystemErrorMessages;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

  private final ClientDao clientDao;
  private DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");

  public ClientDto saveNewClient(ClientDto clientDto) {
    var client = clientDao.save(parseDtoToBuilderOfModel(clientDto).creationAt(LocalDateTime.now()).build());
    return parseModelToBuilderOfDto(client);
  }

  public void deleteClientById(int id) throws ClientException {
    clientDao.delete(findById(id));
  }

  private Page<ClientDto> getAllClients(Pageable page) {
    return clientDao.findAll(page).map(this::parseModelToBuilderOfDto);
  }

  private ClientBuilder parseDtoToBuilderOfModel(ClientDto clientDto) {
    return Client.builder()
        .name(clientDto.getName())
        .cpf(clientDto.getCpf())
        .bornDate(clientDto.getBronDate());
  }

  private ClientDto parseModelToBuilderOfDto(Client client) {

    return ClientDto.builder()
        .id(client.getId())
        .name(client.getName())
        .cpf(client.getCpf())
        .age(calculatingAge(client.getBornDate()))
        .creationAt(client.getCreationAt().format(formattedDate))
        .bronDate(client.getBornDate())
        .updatedAt(client.getUpdatedAt() != null ? client.getUpdatedAt()
            .format(formattedDate) : "").build();
  }

  private Client findById(int id) throws ClientException {
    return clientDao.findById(id).orElseThrow(() -> new ClientException(SystemErrorMessages.CLIENT_NOT_FOUND));
  }

  private int calculatingAge(LocalDate bornDate) {
    return LocalDate.now().getYear() - bornDate.getYear();
  }

  public ClientDto updateClient(ClientDto clientDto) throws ClientException {
    Client client = findById(clientDto.getId());
    return parseModelToBuilderOfDto(clientDao.save(parseDtoToBuilderOfModel(clientDto)
        .id(client.getId())
        .creationAt(client.getCreationAt())
        .updatedAt(LocalDateTime.now())
        .build()));
  }

  public Page<ClientDto> getClients(Pageable page, String nome, String cpf) {
    return (!nome.isBlank() || !cpf.isBlank()) ?
        clientDao.findAllByNameOrCpf(page, nome, cpf).map(this::parseModelToBuilderOfDto) :
        getAllClients(page);
  }
}
