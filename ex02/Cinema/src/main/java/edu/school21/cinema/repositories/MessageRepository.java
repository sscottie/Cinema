package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> findAllByFilm(Film film, Integer offset, Integer limit);
    void save(Message message);
}
