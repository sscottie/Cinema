package edu.school21.cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class MessageInDto {

    /** текст сообщения */
    String text;
    /** отправитель сообщения */
    Long authorId;

    @JsonCreator
    public MessageInDto(@JsonProperty("text") String text, @JsonProperty("authorId") Long authorId) {
        this.text = text;
        this.authorId = authorId;
    }
}
