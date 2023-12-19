package br.com.mobi.codeinterview.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static LocalDateTime fromString(String dateFormatted, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, new Locale("en"));
        return ZonedDateTime.parse(dateFormatted, formatter).toLocalDateTime();
    }
}
