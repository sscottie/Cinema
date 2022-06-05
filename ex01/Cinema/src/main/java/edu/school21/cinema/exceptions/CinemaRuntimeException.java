package edu.school21.cinema.exceptions;

public class CinemaRuntimeException extends RuntimeException {

	private final Integer statusCode;

	public CinemaRuntimeException(String message, Integer statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public CinemaRuntimeException(String message, Integer statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
}
