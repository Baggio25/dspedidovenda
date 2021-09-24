package com.baggio.pedidovendabackend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baggio.pedidovendabackend.domain.Cliente;
import com.baggio.pedidovendabackend.dto.ClienteDTO;
import com.baggio.pedidovendabackend.repositories.ClienteRepository;
import com.baggio.pedidovendabackend.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void initialize(ClienteUpdate ann) {
  }

  @Override
  public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

    Map<String, String> map = (Map<String, String>) request
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Long uriId = Long.parseLong(map.get("id"));

    List<FieldMessage> list = new ArrayList<>();

    Cliente clienteAux = clienteRepository.findByEmail(objDto.getEmail());
    if (clienteAux != null && !clienteAux.getId().equals(uriId)) {
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
