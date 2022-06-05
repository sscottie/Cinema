package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmRepositoryEntityManagerImpl implements FilmRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Film> findAll(){
		return entityManager.createQuery("SELECT film FROM Film film ORDER BY film.id", Film.class).getResultList();
	}

	@Override
	public Film save(Film entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Film findById(Long id) {
		return entityManager.createQuery("SELECT film FROM Film film WHERE film.id =: id", Film.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
