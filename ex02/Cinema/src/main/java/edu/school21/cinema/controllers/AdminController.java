package edu.school21.cinema.controllers;

import edu.school21.cinema.dto.FilmInDto;
import edu.school21.cinema.dto.FilmSessionInDto;
import edu.school21.cinema.dto.FilmSessionOutDto;
import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.services.AdminService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** Controller for admin interaction with Cinema */
@Controller
@RequestMapping("/admin/panel")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("halls")
	public String getHalls(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("halls", adminService.getHalls());
		return "halls";
	}

	@PostMapping("halls")
	public String createHall(HallInDto dto) {
		adminService.createHall(dto);
		return "redirect:/admin/panel/halls";
	}

	@GetMapping("films")
	public  String getFilms(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("films", adminService.getFilms());
		return "films";
	}

	@PostMapping("films")
	public String createFilm(FilmInDto dto) {
		adminService.createFilm(dto);
		return  "redirect:/admin/panel/films";
	}

	@ResponseBody
	@GetMapping("film/{filmId}/poster")
	public void getFilmPoster(@PathVariable long filmId, HttpServletRequest request, HttpServletResponse response) {
		adminService.getFilmPoster(filmId, request, response);
	}

	@PostMapping("film/{filmId}/poster")
	public String uploadFilmPoster(@PathVariable long filmId, @RequestParam("image") MultipartFile image) {
		adminService.uploadFilmPoster(filmId, image);
		return "redirect:/admin/panel/films";
	}

	@GetMapping("sessions")
	public String getSessions(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("sessions", adminService.getSessions());
		model.addAttribute("films", adminService.getFilms());
		model.addAttribute("halls", adminService.getHalls());
		return "sessions";
	}

	@ResponseBody
	@GetMapping("sessions/search")
	public List<FilmSessionOutDto> searchSessions(@RequestParam(value = "filmName", required = false) @Nullable String filmTitle,
												  @ModelAttribute("model") ModelMap model) {
		return adminService.searchSessions(filmTitle);
	}

	@PostMapping("sessions")
	public String createSession(FilmSessionInDto dto) {
		adminService.createSession(dto);
		return  "redirect:/admin/panel/sessions";
	}
}