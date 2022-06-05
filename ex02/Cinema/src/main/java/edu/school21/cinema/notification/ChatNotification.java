package edu.school21.cinema.notification;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@Setter
@Value
public class ChatNotification {

    /** текст сообщения */
    String text;

    /** отправитель сообщения */
    Long authorId;

    /** дата сообщения */
    LocalDateTime dateTimeCreate;

    /** id фильма - чтобы было понятно в каком фильме отрисовывать сообщение*/
    Long filmId;
}
