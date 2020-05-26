package com.slisane.schedule.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    public static ZonedDateTime parse(String input) {
        return ZonedDateTime.parse(input, FORMATTER);
    }

    //TODO: inject formatter universally into all datetime operations


}
