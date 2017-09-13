package net.etfbl.hcc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class TemporalStringConverters {

	private static final String DATE_PATTERN = "dd.MM.yyyy.";
	private static final String TIME_PATTERN = "HH:mm:ss -";
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);

	// Suppresses default constructor, ensuring non-instantiability.
	private TemporalStringConverters() {}

	public static StringConverter<LocalDate> getLocalDateConverter() {
		return new StringConverter<LocalDate>() {

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}

			@Override
			public String toString(LocalDate value) {
				return TemporalStringConverters.toString(value);
			}

		};
	}

	public static StringConverter<LocalTime> getLocalTimeConverter() {
		return new StringConverter<LocalTime>() {

			@Override
			public LocalTime fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalTime.parse(string, timeFormatter);
				} else {
					return null;
				}
			}

			@Override
			public String toString(LocalTime value) {
				return TemporalStringConverters.toString(value);
			}

		};
	}

	public static String toString(LocalDateTime value) {
		if (value != null) {
			return timeFormatter.format(value.toLocalTime()) + " " + dateFormatter.format(value.toLocalDate());
		} else {
			return "";
		}
	}

	public static String toString(LocalDate value) {
		if (value != null) {
			return dateFormatter.format(value);
		} else {
			return "";
		}
	}

	public static String toString(LocalTime value) {
		if (value != null) {
			return timeFormatter.format(value);
		} else {
			return "";
		}
	}

}
