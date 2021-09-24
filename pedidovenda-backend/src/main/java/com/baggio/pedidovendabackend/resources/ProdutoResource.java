package com.baggio.pedidovendabackend.resources;

import java.net.URI;
import java.util.List;

import com.baggio.pedidovendabackend.domain.Produto;
import com.baggio.pedidovendabackend.dto.ProdutoDTO;
import com.baggio.pedidovendabackend.resources.utils.Url;
import com.baggio.pedidovendabackend.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Long id) {
		Produto produto = produtoService.find(id);
		return ResponseEntity.ok().body(produto);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String nomeDecoded = Url.decodeParam(nome);
		List<Long> ids = Url.decodeLongList(categorias);
		Page<Produto> produtos = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDTO = produtos.map(produto -> new ProdutoDTO(produto));

		return ResponseEntity.ok().body(produtosDTO);
	}

	@PostMapping(value = "/picture/{id}")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file, @PathVariable Long id) {
		URI uri = produtoService.uploadPicture(file, id); 
		return ResponseEntity.created(uri).build();
	}

}
