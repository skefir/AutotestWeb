package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    private static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm O", Locale.ENGLISH);

    public static LocalDate convertDate(String strDate) {
        return LocalDate.parse(strDate, dataFormatter);
    }

    public static LocalDateTime convertDateTime(String strDate) {
        return LocalDateTime.parse(strDate, dataTimeFormatter);
    }
}
