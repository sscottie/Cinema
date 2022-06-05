package edu.school21.cinema.dto;

import edu.school21.cinema.models.Film;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

@Value
public class FilmOutDto {

	/** Id фильма */
	Long id;
	/** Название */
	String title;
	/** Год выпуска */
	Integer yearOfRelease;
	/** Ограничение возраста */
	Byte ageRestrictions;
	/** Описание */
	@Nullable
	String description;
	/** Длительность */
	Integer duration;
	/** Ссылка на постер */
	String posterUrl;

	public FilmOutDto(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.yearOfRelease = film.getYearOfRelease();
		this.ageRestrictions = film.getAgeRestrictions();
		this.description = film.getDescription();
		this.duration = film.getDuration();
		this.posterUrl = String.format("/admin/panel/film/%d/poster", film.getId());
	}
}
