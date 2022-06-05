package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallRepositoryEntityManagerImpl implements HallRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Hall> findAll() {
		return entityManager.createQuery("FROM Hall", Hall.class).getResultList();
	}

	@Override
	public Hall save(Hall entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Hall findById(Long id) {
		return entityManager.createQuery("SELECT hall FROM Hall hall WHERE hall.id =: id", Hall.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}