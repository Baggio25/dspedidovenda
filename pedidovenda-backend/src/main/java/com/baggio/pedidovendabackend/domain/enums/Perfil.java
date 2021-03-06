package com.baggio.pedidovendabackend.domain.enums;

public enum Perfil {

  ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

  private int codigo;
  private String descricao;

  private Perfil(int codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public static Perfil toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    for (Perfil perfil : Perfil.values()) {
      if (cod.equals(perfil.getCodigo())) {
        return perfil;
      }
    }

    throw new IllegalArgumentException("Id inválido: " + cod);
  }

  public int getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

}
