package com.baggio.pedidovendabackend.services;

import java.util.List;
import java.util.Optional;

import com.baggio.pedidovendabackend.domain.Categoria;
import com.baggio.pedidovendabackend.dto.CategoriaDTO;
import com.baggio.pedidovendabackend.repositories.CategoriaRepository;
import com.baggio.pedidovendabackend.services.exceptions.DataIntegrityException;
import com.baggio.pedidovendabackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public Categoria find(Long id) {
    Optional<Categoria> obj = categoriaRepository.findById(id);
    return obj.orElseThrow((() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
  }

  public List<Categoria> findAll() {
    return categoriaRepository.findAll();
  }

  public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return categoriaRepository.findAll(pageRequest);
  }

  @Transactional
  public Categoria insert(Categoria categoria) {
    categoria.setId(null);
    return categoriaRepository.save(categoria);
  }

  public Categoria update(Categoria categoria) {
    Categoria categoriaSalva = find(categoria.getId());
    updateData(categoriaSalva, categoria);
    return categoriaRepository.save(categoriaSalva);
  }

  public void delete(Long id) {
    try {
      categoriaRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir categoria com produtos vinculados");
    }

  }

  public Categoria fromDTO(CategoriaDTO categoriaDTO) {
    return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
  }

  private void updateData(Categoria categoriaSalva, Categoria categoria) {
    categoriaSalva.setNome(categoria.getNome());
  }

}
