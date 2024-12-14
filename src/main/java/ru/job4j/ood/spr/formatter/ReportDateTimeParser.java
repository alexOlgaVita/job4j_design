package ru.job4j.ood.spr.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportDateTimeParser implements DateTimeParser<Calendar> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public static SimpleDateFormat getDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public String parse(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }
}
