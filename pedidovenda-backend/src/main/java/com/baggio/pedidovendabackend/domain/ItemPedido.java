package com.baggio.pedidovendabackend.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double desconto;
  private Double preco;
  private Integer quantidade;

  public ItemPedido() {
  }

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Double preco, Integer quantidade) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.preco = preco;
    this.quantidade = quantidade;
  }

  public ItemPedidoPK getId() {
    return this.id;
  }

  public void setId(ItemPedidoPK id) {
    this.id = id;
  }

  public Double getDesconto() {
    return this.desconto;
  }

  public void setDesconto(Double desconto) {
    this.desconto = desconto;
  }

  public Double getPreco() {
    return this.preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public Integer getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  public void setPedido(Pedido pedido) {
    id.setPedido(pedido);
  }

  public Produto getProduto() {
    return id.getProduto();
  }

  public void setProduto(Produto produto) {
    id.setProduto(produto);
  }

  public double getSubTotal() {
    return (preco - desconto) * quantidade;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof ItemPedido)) {
      return false;
    }
    ItemPedido itemPedido = (ItemPedido) o;
    return Objects.equals(id, itemPedido.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

@Override
public String toString() {
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	StringBuilder builder = new StringBuilder();
	builder.append(getProduto().getNome());
	builder.append(", Qte: ");
	builder.append(getQuantidade());
	builder.append(", Preço Unitário: ");
	builder.append(nf.format(getPreco()));
	builder.append(", Subtotal: ");
	builder.append(nf.format(getSubTotal()));
	builder.append("\n");	
	
	return builder.toString();
}

  
  
}
