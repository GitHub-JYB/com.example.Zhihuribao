package com.example.zhihuribao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
    private static final Calendar c = Calendar.getInstance();
    private static String date = setOneMoreDay();

    public static String getPreviousDay() {
        try {
            c.setTime(sdf.parse(date));
            c.add(Calendar.DATE, -1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        date = sdf.format(c.getTime());

        return date;
    }

    private static String setOneMoreDay() {
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);

        return sdf.format(c.getTime());
    }
}
