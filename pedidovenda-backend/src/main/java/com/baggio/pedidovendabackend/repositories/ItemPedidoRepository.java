package com.baggio.pedidovendabackend.repositories;

import com.baggio.pedidovendabackend.domain.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
