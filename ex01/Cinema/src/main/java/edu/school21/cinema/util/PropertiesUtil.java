package edu.school21.cinema.util;

import edu.school21.cinema.exceptions.CinemaRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtil {

	private static final Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();

		try	(FileInputStream fis = new FileInputStream("src/main/webapp/WEB-INF/application.properties")) {
			PROPERTIES.load(fis);
		} catch (IOException e) {
			throw new CinemaRuntimeException("Error during properties read", HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
		}
	}

	public static String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}
}