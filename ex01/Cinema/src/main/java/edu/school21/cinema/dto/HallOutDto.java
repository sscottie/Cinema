package edu.school21.cinema.dto;

import edu.school21.cinema.models.Hall;
import lombok.Value;

@Value
public class HallOutDto {

	/** Id зала */
	Long id;
	/** Количество мест */
	Integer seatsCount;
	/** Свободный зал */
	Boolean vacant;

	public HallOutDto (Hall hall) {
		this.id = hall.getId();
		this.seatsCount = hall.getSeatsCount();
		this.vacant = Boolean.TRUE;
	}
}
