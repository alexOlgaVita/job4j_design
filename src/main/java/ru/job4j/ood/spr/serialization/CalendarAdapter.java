package ru.job4j.ood.spr.serialization;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import ru.job4j.ood.spr.formatter.DateTimeParser;

import java.util.Calendar;

import static ru.job4j.ood.spr.formatter.ReportDateTimeParser.getDateFormat;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

    private final DateTimeParser<Calendar> dateTimeParser;

    public CalendarAdapter(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public Calendar unmarshal(String xml) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFormat().parse(xml));
        return cal;
    }

    @Override
    public String marshal(Calendar object) {
        return dateTimeParser.parse(object);
    }
}
