package com.generation.nostalgames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.nostalgames.model.Categoria;
import com.generation.nostalgames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping // buscar por todos registros da tabela /categorias
	public ResponseEntity<List<Categoria>> getAll() {

		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("/{id}") // buscar por um id da tabela /categorias
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {

		return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping // registrar
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) {

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}

	@PutMapping // atualizar
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {
		return categoriaRepository.findById(categoria.getId()).map(resp -> {
			return ResponseEntity.ok().body(categoriaRepository.save(categoria));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping ("/{id}") // deletar pelo id
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {

		return categoriaRepository.findById(id).map(resposta -> {
			categoriaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}") // criação de método pela interface Repository. Buscar por letras da descrição.
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable String descricao) {

		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

}
