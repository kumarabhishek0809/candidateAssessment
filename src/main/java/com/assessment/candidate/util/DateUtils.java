package com.assessment.candidate.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static String getStringDate(ZonedDateTime zonedDateTime){
        String formattedString2 = null;
        if(zonedDateTime  != null ) {
            formattedString2 = zonedDateTime.format(formatter2);
        }
        return formattedString2;
    }
}
