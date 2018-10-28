package java8_in_action.part12;

import java.time.*;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @Author lx
 * @Date 2018/10/27 21:39
 */
public class DateTimeExample {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2018, 10, 28);
        System.out.println(d1);
        int year = d1.getYear();
        System.out.println(year);
        int dayOfMonth = d1.getDayOfMonth();
        DayOfWeek dayOfWeek = d1.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(dayOfMonth);

        Era era = d1.getEra();
        System.out.println(era);
        LocalDateTime localDateTime = d1.atStartOfDay();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = d1.atTime(10, 10, 10);
        System.out.println(localDateTime1);

        int i = d1.get(ChronoField.YEAR_OF_ERA);
        System.out.println(i);


        LocalTime t1 = LocalTime.of(13, 45, 20);
        System.out.println(t1);
        int hour = t1.getHour();
        System.out.println(hour);

        LocalDateTime of = LocalDateTime.of(2019, 01, 10, 20, 20, 20);
        System.out.println(of);
        LocalDateTime localDateTime2 = d1.atTime(22, 22, 22);
        System.out.println(localDateTime2);
        LocalDateTime localDateTime3 = t1.atDate(d1);
        System.out.println(localDateTime3);

        LocalDate localDate = of.toLocalDate();
        LocalTime localTime = of.toLocalTime();

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        System.out.println(instant);

        Instant now = Instant.now();
        System.out.println(now);

        Duration between = Duration.between(LocalTime.of(13, 45, 10), t1);
        System.out.println(between);

        Duration of1 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(of1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y/d/m");
        String format = of.format(formatter);
        System.out.println(format);

        String format1 = of.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(format1);

        DateTimeFormatter builder = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseSensitive()
                .toFormatter(Locale.ITALIAN);

        String format2 = of.format(builder);
        System.out.println(format2);
    }
}
