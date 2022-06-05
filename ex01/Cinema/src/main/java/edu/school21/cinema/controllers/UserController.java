package edu.school21.cinema.controllers;

import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
