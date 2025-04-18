package pl.kurs.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateUtils {

    public static int getDayOfTheWeek(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("pl", "PL"));
        LocalDate localDate = LocalDate.parse(date, format);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public static String getDayOfWeekByNumber(int number) {
        return DayOfWeek.of(number).getDisplayName(TextStyle.FULL, new Locale("pl", "PL"));
    }

    public static int getYearFromDateInString(String date) {
        DateTimeFormatter flexiFormat = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral("-")
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("-")
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter();

        LocalDate formattedDate = LocalDate.parse(date, flexiFormat);
        return formattedDate.getYear();
    }

    public static LocalDate getDateFromString(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsed = LocalDate.parse(date, format);
        return parsed;
    }
}
