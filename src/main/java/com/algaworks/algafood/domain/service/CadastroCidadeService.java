package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoReepository;
	
	public Cidade adicionar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoReepository.buscarPorId(estadoId);
		
		if (estado == null) {
			
			throw new EntidadeNaoEncontradaException(String.format("Não existe Estado de id: %d", estadoId));
		}
		
		cidade.setEstado(estado);
		return cidadeRepository.salvar(cidade);
	}
	
	public void remover(Long cidadeId) {
		
		try {
			
			cidadeRepository.excluir(cidadeId);
		
		}catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format("Cidade de id %d, não pode ser removido, pois esta em uso!", cidadeId));
		
		}catch(EmptyResultDataAccessException e){
			
			throw new EntidadeNaoEncontradaException(String.format("Cidade de Id %d, não encontrado!!!", cidadeId));
		}
	}
}
