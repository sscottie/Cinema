package edu.school21.cinema.dto;

import lombok.Value;

import java.util.List;

@Value
public class FilmChatOutDto {

	/** Film */
	FilmOutDto film;
	/** Created user id */
	Long userId;
	/** First 20 messages */
	List<MessageOutDto> messages;
}
