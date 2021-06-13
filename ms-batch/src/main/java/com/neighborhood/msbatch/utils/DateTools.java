package com.neighborhood.msbatch.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

    public static String dateToStringPatternForEmail (Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
