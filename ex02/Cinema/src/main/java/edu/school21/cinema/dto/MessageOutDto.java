package edu.school21.cinema.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.serialization.LocalDateTimeSerializer;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Value
public class MessageOutDto {

    /** id  фильма */
    Long filmId;
    /** отправитель сообщения */
    Long authorId;
    /** дата создания объекта сообщения */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime dateTimeCreate;
    /** текст сообщения */
    String text;

    public MessageOutDto(Message message){
        filmId = message.getId();
        authorId = message.getAuthor().getId();
        dateTimeCreate = message.getDateTimeCreate();
        text = message.getText();
    }
}
