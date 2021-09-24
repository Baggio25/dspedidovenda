package com.baggio.pedidovendabackend.repositories;

import java.util.List;

import com.baggio.pedidovendabackend.domain.Categoria;
import com.baggio.pedidovendabackend.domain.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias cat "
            + " WHERE p.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
            Pageable pageRequest);

}
