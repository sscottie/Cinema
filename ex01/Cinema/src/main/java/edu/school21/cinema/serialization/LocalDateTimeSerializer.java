package edu.school21.cinema.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	private static final ZoneOffset ZONE_OFFSET = OffsetDateTime.now().getOffset();

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
		jGen.writeString(dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
	}
}
