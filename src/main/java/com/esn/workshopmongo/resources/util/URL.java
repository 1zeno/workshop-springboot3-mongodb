package com.esn.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class URL {

	public static String decodePrams(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant convertDate(String textDate, Instant defaultValue) {
		if (textDate == null || textDate.trim().isEmpty()) {
            return defaultValue;
        }
		LocalDate localDate = LocalDate.parse(textDate);
        ZoneId zoneId = ZoneId.of("GMT");
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Instant date = zonedDateTime.toInstant();
		return date;
	}
}
