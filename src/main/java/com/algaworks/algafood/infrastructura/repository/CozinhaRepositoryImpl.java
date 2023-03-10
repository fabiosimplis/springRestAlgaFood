package com.algaworks.algafood.infrastructura.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public List<Cozinha> listar(){
		
//		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
//		
//		return query.getResultList();
				
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	

	@Override
	public List<Cozinha> consultaPorNome(String nomeCozinha) {
		
		return manager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
				.setParameter("nome", nomeCozinha)
				.getResultList();
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		
		return manager.merge(cozinha);	
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		
		Cozinha cozinha = buscar(id);
		
		if (cozinha == null) {
			
			throw new EmptyResultDataAccessException(1);// esperava uma cozinha
		}
		
		manager.remove(cozinha);
	}




	
}
