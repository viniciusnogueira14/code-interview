package br.com.mobi.codeinterview.unit.util;

import br.com.mobi.codeinterview.util.DateUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "Dec 20 2018 10:45:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "2018-12-20 10:45:58 -02, uuuu-MM-dd HH:mm:ss X",
            "20/12/2018T10:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X"
    })
    void convertDateFromString(String formattedDate, String format) {
        LocalDateTime convertedDate = DateUtils.fromString(formattedDate, format);
        assertEquals(2018, convertedDate.getYear());
        assertEquals(12, convertedDate.getMonthValue());
        assertEquals(Month.DECEMBER, convertedDate.getMonth());
        assertEquals(20, convertedDate.getDayOfMonth());
        assertEquals(10, convertedDate.getHour());
        assertEquals(45, convertedDate.getMinute());
        assertEquals(58, convertedDate.getSecond());
    }

    @ParameterizedTest
    @CsvSource({
            "Mon 20 2018 10:45:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "Dec 32 2018 10:45:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "Dec 20 199 10:45:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "Dec 20 2018 25:45:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "Dec 20 2018 10:62:58 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "Dec 20 2018 10:45:65 -0200, MMM dd uuuu HH:mm:ss ZZZ",
            "999-12-20 10:45:58 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-15-20 10:45:58 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-12-32 10:45:58 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-12-20 25:45:58 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-12-20 10:60:58 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-12-20 10:45:63 -02, uuuu-MM-dd HH:mm:ss X",
            "2018-12-20 10:45:58 -22, uuuu-MM-dd HH:mm:ss X",
            "32/12/2018T10:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/13/2018T10:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/018T10:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/2018P10:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/2018T24:45:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/2018T10:76:58.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/2018T10:45:68.-02, dd/MM/uuuu'T'HH:mm:ss.X",
            "20/12/2018T10:45:58.-20, dd/MM/uuuu'T'HH:mm:ss.X"
    })
    void convertDateFromString_ThrowException(String formattedDate, String format) {
        assertThrows(DateTimeParseException.class,
                () -> DateUtils.fromString(formattedDate, format));
    }
}
