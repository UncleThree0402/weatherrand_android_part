package dataformatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatter {

    public static String datetimeToHour(String datetime){
        return timeString(Long.parseLong(datetime),"HH");
    }

    private static String timeString(long timeStamp, String format) {
        Date date = new Date(timeStamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        return dateFormat.format(date);
    }

    public static String timeStringToTomorrow(String datetime){
        return timeString(Long.parseLong(datetime), "E MM-dd");
    }

}
