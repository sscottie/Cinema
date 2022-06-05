package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;

import java.util.List;

public interface FilmRepository {

	List<Film> findAll();
	Film save(Film entity);
	Film findById(Long id);
}
