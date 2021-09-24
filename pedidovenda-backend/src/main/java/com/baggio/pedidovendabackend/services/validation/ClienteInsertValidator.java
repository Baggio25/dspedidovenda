package com.baggio.pedidovendabackend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baggio.pedidovendabackend.domain.Cliente;
import com.baggio.pedidovendabackend.domain.enums.TipoCliente;
import com.baggio.pedidovendabackend.dto.ClienteNewDTO;
import com.baggio.pedidovendabackend.repositories.ClienteRepository;
import com.baggio.pedidovendabackend.resources.exceptions.FieldMessage;
import com.baggio.pedidovendabackend.services.validation.util.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void initialize(ClienteInsert ann) {
  }

  @Override
  public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
    }

    if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
      list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
    }

    Cliente clienteAux = clienteRepository.findByEmail(objDto.getEmail());
    if (clienteAux != null) {
      list.add(new FieldMessage("email", "E-mail já existente"));
    }

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
          .addConstraintViolation();
    }
    return list.isEmpty();
  }
}
