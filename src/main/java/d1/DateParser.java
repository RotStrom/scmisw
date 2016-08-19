package d1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * throws an exception because SimpleDateFormat isn't thread-safe
 * in fact, couldn't get exception
 */
public class DateParser {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Date parse(String s) throws ParseException {
        return format.parse(s);
    }

    public static void main(String[] args) {
        DateParser parser = new DateParser();

        Thread t1 = new Thread(() -> {
            try {
                parser.parse("2016-05-14");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                parser.parse("2016-05-15");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
