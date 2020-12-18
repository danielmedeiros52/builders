package com.builders.validation.dao;

import com.builders.validation.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

  Page<Client> findAllByNameOrCpf(Pageable pageable, String name, String cpf);
}
