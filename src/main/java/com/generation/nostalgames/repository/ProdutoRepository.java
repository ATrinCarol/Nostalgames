package com.generation.nostalgames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.nostalgames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//seleciona os jogos que contém as letras que você digitou na busca

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String Nome);

//Seleciona valores menores ou iguais do que o critério da consulta \/

	public List<Produto> findByPrecoLessThanEqual(BigDecimal Preco);

//Seleciona valores maiores ou iguais do que o critério da consulta \/

	public List<Produto> findByPrecoGreaterThanEqual(BigDecimal preco);

//	public List <Produto> findAllByPrecoSave(BigDecimal preco);
}
