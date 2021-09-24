package com.baggio.pedidovendabackend.services;

import java.util.Calendar;
import java.util.Date;

import com.baggio.pedidovendabackend.domain.PagamentoComBoleto;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

  public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(instanteDoPedido);
    cal.add(Calendar.DAY_OF_MONTH, 30); // Gera vcto para 30 dias a partir da data da gravação do pedido
    pagto.setDataVencimento(cal.getTime());
  }

}
