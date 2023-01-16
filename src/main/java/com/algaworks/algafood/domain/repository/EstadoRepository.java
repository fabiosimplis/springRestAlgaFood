package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;

public interface EstadoRepository {
	
	
	List<Estado> listar();
	Estado buscarPorId(Long id);
	Estado adicionar(Estado estado);
	void excluir(Long id);
}
