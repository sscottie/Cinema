package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmSessionRepositoryEntityManagerImpl implements FilmSessionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<FilmSession> findAllByHall(Hall hall) {
		return entityManager.createQuery("SELECT session " +
								"FROM FilmSession session " +
								"WHERE session.hall = :hall " +
								"ORDER BY session.sessionDateTimeFrom ASC",
						FilmSession.class)
				.setParameter("hall", hall)
				.getResultList();
	}

	@Override
	public void save(FilmSession filmSession) {
		entityManager.persist(filmSession);
	}

	@Override
	public List<FilmSession> findAll() {
		return entityManager.createQuery(
				"SELECT session FROM FilmSession session ORDER BY session.sessionDateTimeFrom ASC",
						FilmSession.class)
				.getResultList();
	}

	@Override
	public List<FilmSession> findAllByFilmTitle(String title) {
		return entityManager.createQuery("SELECT session " +
								"FROM FilmSession session JOIN session.film film " +
								"WHERE LOWER(film.title) LIKE :pattern " +
								"ORDER BY session.sessionDateTimeFrom ASC",
						FilmSession.class)
				.setParameter("pattern", "%" + title.toLowerCase() + "%")
				.getResultList();
	}

	@Override
	public FilmSession findById(long id) {
		return entityManager.createQuery("SELECT session FROM FilmSession session WHERE session.id = :id", FilmSession.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
