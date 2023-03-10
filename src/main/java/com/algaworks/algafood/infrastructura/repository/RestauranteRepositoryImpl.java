package com.algaworks.algafood.infrastructura.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public List<Restaurante> listar(){
		
//		TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);
//		
//		return query.getResultList();
				
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}
	
	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class , id);
	}
	
	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		
		return manager.merge(restaurante);	
	}
	
	
	@Transactional
	@Override
	public void remover(Long restauranteId) {
		
		Restaurante restaurante = buscar(restauranteId);
		
		if(restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(restaurante);
	}
	
	
}
