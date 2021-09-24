package com.baggio.pedidovendabackend.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baggio.pedidovendabackend.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tb_pagamento_dinheiro")
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends Pagamento {

  private static final long serialVersionUID = 1L;

  private Double percentualDesconto;
  private Double valorTroco;

  public PagamentoComDinheiro() {
  }

  public PagamentoComDinheiro(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Double percentualDesconto,
      Double valorTroco) {
    super(id, estadoPagamento, pedido);
    this.percentualDesconto = percentualDesconto;
    this.valorTroco = valorTroco;
  }

  public Double getPercentualDesconto() {
    return this.percentualDesconto;
  }

  public void setPercentualDesconto(Double percentualDesconto) {
    this.percentualDesconto = percentualDesconto;
  }

  public Double getValorTroco() {
    return this.valorTroco;
  }

  public void setValorTroco(Double valorTroco) {
    this.valorTroco = valorTroco;
  }

}
