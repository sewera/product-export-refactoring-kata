package codingdojo;

import java.text.*;
import java.util.*;

class DateUtil {
    private static final String TIME_ZONE = "UTC";
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm'Z'";

    public static Date fromIsoDate(String datetime) {
        TimeZone tz = TimeZone.getTimeZone(TIME_ZONE);
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        df.setTimeZone(tz);

        try {
            return df.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toIsoDate(Date date) {
        TimeZone tz = TimeZone.getTimeZone(TIME_ZONE);
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        df.setTimeZone(tz);
        return df.format(date);
    }
}
