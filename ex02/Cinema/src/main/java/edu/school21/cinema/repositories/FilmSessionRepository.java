package edu.school21.cinema.repositories;

import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Hall;

import java.util.List;

public interface FilmSessionRepository {
	List<FilmSession> findAllByHall(Hall hall);
	void save(FilmSession filmSession);
	List<FilmSession> findAll();
	List<FilmSession> findAllByFilmTitle(String title);
	FilmSession findById(long id);
}
