package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		
		return estadoRepository.adicionar(estado);
	}
	
	public void remover(Long estadoId) {
		
		try {
			
			estadoRepository.excluir(estadoId);
		
		}catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format("Estado de id %d, não pode ser removido, pois esta em uso!", estadoId));
		
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format("Estado de Id %d, não encontrado!!!", estadoId));
		}
	}
}
