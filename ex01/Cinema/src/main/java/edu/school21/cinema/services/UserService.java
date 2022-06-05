package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmSessionOutDto;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.repositories.FilmSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private FilmSessionRepository filmSessionRepository;

	@Transactional(readOnly = true)
	public FilmSessionOutDto getSession(long sessionId) {
		FilmSession session = filmSessionRepository.findById(sessionId);
		if (session == null) {
			throw new CinemaRuntimeException("Session not found", HttpStatus.NOT_FOUND.value());
		}

		return new FilmSessionOutDto(session);
	}

}
