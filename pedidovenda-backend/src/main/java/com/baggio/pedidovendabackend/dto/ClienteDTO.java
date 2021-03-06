package com.baggio.pedidovendabackend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.baggio.pedidovendabackend.domain.Cliente;
import com.baggio.pedidovendabackend.services.validation.ClienteUpdate;

import org.hibernate.validator.constraints.Length;

@ClienteUpdate
public class ClienteDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120 caracteres")
  private String nome;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Email(message = "E-mail inválido")
  private String email;

  public ClienteDTO() {
  }

  public ClienteDTO(Cliente cliente) {
    id = cliente.getId();
    nome = cliente.getNome();
    email = cliente.getEmail();
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
