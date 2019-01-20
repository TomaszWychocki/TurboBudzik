package com.example.turbobudzik;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils
{
    public static String changeDateFormat(String date, String srcFormat, String dstFormat)
    {
        try
        {
            SimpleDateFormat sdfSource = new SimpleDateFormat(srcFormat);
            Date tempDate = sdfSource.parse(date);
            SimpleDateFormat sdfDestination = new SimpleDateFormat(dstFormat);
            return sdfDestination.format(tempDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public static String now()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        return format.format(currentDate);
    }

    public static String now(int offset)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + offset);

        return format.format(currentDate);
    }
}
