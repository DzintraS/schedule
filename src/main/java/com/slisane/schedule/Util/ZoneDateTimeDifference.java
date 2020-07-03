package com.slisane.schedule.Util;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ZoneDateTimeDifference {

    static long zoneDateTimeDifference(ZonedDateTime d1, ZonedDateTime d2, ChronoUnit unit){
        return unit.between(d1, d2);
    }
}
