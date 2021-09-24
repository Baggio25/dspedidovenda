package com.baggio.pedidovendabackend.repositories;

import com.baggio.pedidovendabackend.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
