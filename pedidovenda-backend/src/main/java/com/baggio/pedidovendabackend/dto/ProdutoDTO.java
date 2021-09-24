package com.baggio.pedidovendabackend.dto;

import java.io.Serializable;

import com.baggio.pedidovendabackend.domain.Produto;

public class ProdutoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private Double preco;

  public ProdutoDTO() {
  }

  public ProdutoDTO(Produto produto) {
    id = produto.getId();
    nome = produto.getNome();
    preco = produto.getPreco();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getPreco() {
    return this.preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

}
