package com.baggio.pedidovendabackend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.baggio.pedidovendabackend.domain.Categoria;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
  private String nome;

  public CategoriaDTO() {
  }

  public CategoriaDTO(Categoria categoria) {
    id = categoria.getId();
    nome = categoria.getNome();
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

}
