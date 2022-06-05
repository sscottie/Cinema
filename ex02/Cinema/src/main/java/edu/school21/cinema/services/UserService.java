package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmChatOutDto;
import edu.school21.cinema.dto.FilmOutDto;
import edu.school21.cinema.dto.FilmSessionOutDto;
import edu.school21.cinema.dto.MessageInDto;
import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.notification.ChatNotification;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.repositories.FilmSessionRepository;
import edu.school21.cinema.repositories.MessageRepository;
import edu.school21.cinema.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private FilmSessionRepository filmSessionRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;


    @Transactional(readOnly = true)
    public FilmSessionOutDto getSession(long sessionId) {
        FilmSession session = filmSessionRepository.findById(sessionId);
        if (session == null) {
            throw new CinemaRuntimeException("Session not found", HttpStatus.NOT_FOUND.value());
        }

        return new FilmSessionOutDto(session);
    }

    @Transactional
    public FilmChatOutDto getFilmChat(long filmId, @Nullable Long userId, HttpServletResponse response) {

        User user;
        if (userId == null) {
            user = new User();
            userRepository.save(user);

			response.addCookie(new Cookie("userId", String.valueOf(user.getId())));
        } else {
            user = userRepository.findUserById(userId);
            if (user == null) {
                throw new CinemaRuntimeException("User not found", HttpStatus.NOT_FOUND.value());
            }
        }

        Film film = filmRepository.findById(filmId);
        if (film == null) {
            throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
        }

        List<MessageOutDto> messages = messageRepository.findAllByFilm(film, 0, 20).stream()
                .map(MessageOutDto::new)
                .collect(Collectors.toList());

        Collections.reverse(messages);

        return new FilmChatOutDto(new FilmOutDto(film), user.getId(), messages);
    }

    @Transactional
    public void sendMessage(MessageInDto dto, long filmId) {
        User user = userRepository.findUserById(dto.getAuthorId());
		if (user == null) {
			throw new CinemaRuntimeException("Author not found", HttpStatus.NOT_FOUND.value());
		}

        Film film = filmRepository.findById(filmId);
        if (film == null) {
            throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
        }

        Message message = new Message();
        message.setText(dto.getText());
        message.setAuthor(user);
        message.setFilm(film);
        messageRepository.save(message);

        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(filmId),
                "/chat/messages",
                new ChatNotification(
                        dto.getText(),
                        dto.getAuthorId(),
                        message.getDateTimeCreate(),
                        filmId));
    }

    public List<MessageOutDto> getMessages(long filmId, int offset, int limit) {
        Film film = filmRepository.findById(filmId);
        if (film == null) {
            throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
        }

        return messageRepository.findAllByFilm(film, offset, limit).stream()
                .map(MessageOutDto::new)
                .collect(Collectors.toList());
    }
}
