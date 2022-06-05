package edu.school21.cinema.controllers;

import edu.school21.cinema.dto.MessageInDto;
import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.services.UserService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** Controller for user interaction with Cinema */
@Controller
@RequestMapping
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("sessions/{sessionId}")
	public String getSession(@PathVariable long sessionId, @ModelAttribute("model") ModelMap model) {
		model.addAttribute("session", userService.getSession(sessionId));
		return "session-info";
	}

	@ResponseBody
	@GetMapping("films/{filmId}/messages")
	public List<MessageOutDto> getMessages(	@PathVariable("filmId") long filmId,
									   		@RequestParam("offset") int offset,
											@RequestParam("limit") int limit) {
		return userService.getMessages(filmId, offset, limit);
	}

	@GetMapping("films/{filmId}/chat")
	public String getFilmChat(@PathVariable long filmId,
							  @CookieValue(value = "userId", required = false) @Nullable Long userId,
							  HttpServletResponse response,
							  @ModelAttribute("model") ModelMap model){
		model.addAttribute("chat", userService.getFilmChat(filmId, userId, response));
		return "film-chat";
	}

	@MessageMapping("film/{filmId}/chat/messages/send")
	public void sendMessage(@DestinationVariable("filmId") long filmId, @Payload MessageInDto dto) {
		userService.sendMessage(dto, filmId);
	}
}
