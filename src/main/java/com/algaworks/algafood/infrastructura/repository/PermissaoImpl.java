package com.algaworks.algafood.infrastructura.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoImpl implements PermissaoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Permissao> listar(){
		
				
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}
	
	@Override
	public Permissao buscaPorId(Long id) {
		return manager.find(Permissao.class , id);
	}
	
	@Transactional
	@Override
	public Permissao adicionar(Permissao Permissao) {
		
		return manager.merge(Permissao);	
	}
	
	
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		
		permissao = buscaPorId(permissao.getId());
		manager.remove(permissao);
	}
	
	
}
