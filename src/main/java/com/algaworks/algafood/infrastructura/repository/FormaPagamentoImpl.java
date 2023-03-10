package com.algaworks.algafood.infrastructura.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public List<FormaPagamento> listar(){
		
				
		return manager.createQuery("from Restaurante", FormaPagamento.class).getResultList();
	}
	
	
	@Override
	public FormaPagamento buscarPorId(Long id) {
		return manager.find(FormaPagamento.class , id);
	}

	@Transactional
	@Override
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		
		return manager.merge(formaPagamento);	
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento restaurante) {
		
		restaurante = buscarPorId(restaurante.getId());
		manager.remove(restaurante);
	}
	
	
}
