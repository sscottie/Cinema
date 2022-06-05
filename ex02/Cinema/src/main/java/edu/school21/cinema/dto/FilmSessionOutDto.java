package edu.school21.cinema.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.serialization.LocalDateTimeSerializer;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class FilmSessionOutDto {

	/** Id сессии */
	Long id;
	/** Зал */
	HallOutDto hall;
	/** Фильм */
	FilmOutDto film;
	/** Время показа, от */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	LocalDateTime sessionDateTimeFrom;
	/** Время показа, от */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	LocalDateTime sessionDateTimeTo;
	/** Стоимость билета */
	Integer ticketCost;

	public FilmSessionOutDto(FilmSession filmSession) {
		this.id = filmSession.getId();
		this.film = new FilmOutDto(filmSession.getFilm());
		this.hall = new HallOutDto(filmSession.getHall());
		this.sessionDateTimeFrom = filmSession.getSessionDateTimeFrom();
		this.sessionDateTimeTo = filmSession.getSessionDateTimeTo();
		this.ticketCost = filmSession.getTicketCost();
	}
}
