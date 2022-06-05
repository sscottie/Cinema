package edu.school21.cinema.dto;

import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Value
public class FilmInDto {

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
}
