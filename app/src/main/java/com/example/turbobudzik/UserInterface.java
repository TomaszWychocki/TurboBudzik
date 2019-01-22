package com.example.turbobudzik;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

public class UserInterface
{
    public static void updateSchedule(List<Lesson> lessons, Context context)
    {
        ListView listView = ((Activity)context).findViewById(R.id.scheduleList);
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for(Lesson lesson : lessons)
        {
            String timeFrom = DateTimeUtils.changeDateFormat(lesson.getFrom(), "yyyy-MM-dd HH:mm:ss", "HH:mm");
            String timeTo = DateTimeUtils.changeDateFormat(lesson.getTo(), "yyyy-MM-dd HH:mm:ss", "HH:mm");
            String dateShort = DateTimeUtils.changeDateFormat(lesson.getFrom(), "yyyy-MM-dd HH:mm:ss", "dd.MM");
            String dayOfWeek = DateTimeUtils.changeDateFormat(lesson.getFrom(), "yyyy-MM-dd HH:mm:ss", "EEEE");

            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("dateShort", dateShort);
            hm.put("dayOfWeek", dayOfWeek);
            hm.put("subject", lesson.getSubject());
            hm.put("room", lesson.getRoom());
            hm.put("timeFromTo", timeFrom + " - " + timeTo);
            hm.put("reservationStatus", lesson.getReservationStatus());
            aList.add(hm);
        }

        String[] from = {"dateShort", "dayOfWeek", "subject", "room", "timeFromTo", "reservationStatus"};
        int[] to = {R.id.dateShort, R.id.dayOfWeek, R.id.subject, R.id.room, R.id.timeFromTo, R.id.reservationStatus};

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, aList, R.layout.schedule_list_item, from, to);
        listView.setAdapter(simpleAdapter);
    }

    public static String getStudentID(Context context)
    {
        EditText editText = ((Activity)context).findViewById(R.id.studentID);
        return editText.getText().toString();
    }
}
