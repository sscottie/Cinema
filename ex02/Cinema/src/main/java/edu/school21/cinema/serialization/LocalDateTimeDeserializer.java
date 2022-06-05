package edu.school21.cinema.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final ZoneOffset ZONE_OFFSET = OffsetDateTime.now().getOffset();

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		if (jp.getText().length() == 0) {
			return null;
		}

		return LocalDateTime.parse(jp.getText(), DateTimeFormatter.ISO_DATE_TIME);
	}
}
