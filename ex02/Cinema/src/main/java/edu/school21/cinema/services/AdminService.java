package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmInDto;
import edu.school21.cinema.dto.FilmOutDto;
import edu.school21.cinema.dto.FilmSessionInDto;
import edu.school21.cinema.dto.FilmSessionOutDto;
import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.dto.HallOutDto;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.FileInfo;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.repositories.FilmSessionRepository;
import edu.school21.cinema.repositories.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

	private final static FileInfo DEFAULT_POSTER;
	private final static String UPLOAD_PATH = "src/main/resources/files/posters/";
	private final static Integer ADDITIONAL_DURATION = 60;

	static {
		DEFAULT_POSTER = new FileInfo();
		DEFAULT_POSTER.setName("default-poster.jpg");
		DEFAULT_POSTER.setType(MimeTypeUtils.IMAGE_JPEG_VALUE);
	}

	@Autowired
	private HallRepository hallRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private FilmSessionRepository filmSessionRepository;

	@Transactional(readOnly = true)
	public List<HallOutDto> getHalls() {
		return hallRepository.findAll().stream()
				.map(HallOutDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void createHall(HallInDto dto) {
		Hall hall = new Hall();
		hall.setSeatsCount(dto.getSeatsCount());
		hallRepository.save(hall);
	}

	@Transactional(readOnly = true)
	public List<FilmOutDto> getFilms() {
		return filmRepository.findAll().stream()
				.map(FilmOutDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void createFilm(FilmInDto dto){
		Film film = new Film();
		film.setTitle(dto.getTitle());
		film.setYearOfRelease(dto.getYearOfRelease());
		film.setAgeRestrictions(dto.getAgeRestrictions());
		film.setDescription(dto.getDescription());
		film.setDuration(dto.getDuration());
		filmRepository.save(film);
	}

	@Transactional(readOnly = true)
	public void getFilmPoster(Long filmId, HttpServletRequest request, HttpServletResponse response) {
		Film film = filmRepository.findById(filmId);
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}

		FileInfo fileInfo;
		String filePath;
		if (film.getPosterFile() != null) {
			fileInfo = film.getPosterFile();
			filePath = UPLOAD_PATH + film.getId();
		} else {
			fileInfo = DEFAULT_POSTER;
			filePath = UPLOAD_PATH + DEFAULT_POSTER.getName();
		}

		try (FileInputStream fis  = new FileInputStream(filePath)) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(fileInfo.getType());
			response.addHeader("Content-Disposition", String.format("filename=\"%s\"", fileInfo.getName()));

			IOUtils.copy(fis, response.getOutputStream());
		}
		catch (FileNotFoundException e) {
			throw new CinemaRuntimeException("Image not found", HttpStatus.NOT_FOUND.value(), e);
		} catch (IOException e) {
			throw new CinemaRuntimeException("Error during image reading", HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
		}
	}

	@Transactional
	public void uploadFilmPoster(long filmId, MultipartFile image) {
		Film film = filmRepository.findById(filmId);
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}

		try (FileOutputStream fos = new FileOutputStream(UPLOAD_PATH + filmId)) {
			IOUtils.copy(image.getInputStream(), fos);

			FileInfo fileInfo = film.getPosterFile();
			if (fileInfo == null) {
				fileInfo = new FileInfo();
				film.setPosterFile(fileInfo);
			}

			fileInfo.setSize(image.getSize());
			fileInfo.setType(image.getContentType());
			fileInfo.setName(image.getOriginalFilename());
			filmRepository.save(film);

		} catch (IOException e) {
			throw new CinemaRuntimeException("Error during image upload", HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
		}
	}

	@Transactional
	public void createSession(FilmSessionInDto dto) {
		Film film = filmRepository.findById(dto.getFilmId());
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}

		Hall hall = hallRepository.findById(dto.getHallId());
		if (hall == null) {
			throw new CinemaRuntimeException("Hall not found", HttpStatus.NOT_FOUND.value());
		}

		LocalDateTime sessionDateTimeFrom = dto.getSessionDateTime();
		LocalDateTime sessionDateTimTo = sessionDateTimeFrom.plusMinutes(film.getDuration() + ADDITIONAL_DURATION);
		assertSessionTime(sessionDateTimeFrom, sessionDateTimTo, hall);

		FilmSession filmSession = new FilmSession();
		filmSession.setFilm(film);
		filmSession.setHall(hall);
		filmSession.setTicketCost(dto.getTicketCost());
		filmSession.setSessionDateTimeFrom(sessionDateTimeFrom);
		filmSession.setSessionDateTimeTo(sessionDateTimTo);
		filmSessionRepository.save(filmSession);
	}

	@Transactional(readOnly = true)
	public List<FilmSessionOutDto> getSessions() {
		return filmSessionRepository.findAll().stream()
				.map(FilmSessionOutDto::new)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<FilmSessionOutDto> searchSessions(@Nullable String filmTitle) {
		return Optional.ofNullable(filmTitle)
				.map(filmSessionRepository::findAllByFilmTitle)
				.orElseGet(() -> filmSessionRepository.findAllByFilmTitle(""))
				.stream()
				.map(FilmSessionOutDto::new)
				.collect(Collectors.toList());
	}


	private void assertSessionTime(LocalDateTime sessionDateTimeFrom, LocalDateTime sessionDateTimeTo, Hall hall) {
		List<FilmSession> filmSessions = filmSessionRepository.findAllByHall(hall);

		for (FilmSession session : filmSessions) {
			LocalDateTime from = session.getSessionDateTimeFrom();
			LocalDateTime to = session.getSessionDateTimeTo();

			if ((sessionDateTimeFrom.isAfter(from) && sessionDateTimeFrom.isBefore(to))
					|| (sessionDateTimeTo.isAfter(from) && sessionDateTimeTo.isBefore(to))) {
				throw new CinemaRuntimeException(String.format("Hall already busy by '%s'",
						session.getFilm().getTitle()),
						HttpStatus.BAD_REQUEST.value());
			}
		}
	}
}
