package codingdojo;

import java.text.*;
import java.util.*;

class DateUtil {
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm'Z'";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    public static Date fromIsoDate(String datetime) {
        DATE_FORMAT.setTimeZone(TIME_ZONE);

        try {
            return DATE_FORMAT.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toIsoDate(Date date) {
        DATE_FORMAT.setTimeZone(TIME_ZONE);
        return DATE_FORMAT.format(date);
    }
}
