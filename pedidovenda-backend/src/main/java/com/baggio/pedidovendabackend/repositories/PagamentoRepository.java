package com.baggio.pedidovendabackend.repositories;

import com.baggio.pedidovendabackend.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
