package com.baggio.pedidovendabackend.repositories;

import com.baggio.pedidovendabackend.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  @Transactional(readOnly = true)
  Cliente findByEmail(String email);

}
