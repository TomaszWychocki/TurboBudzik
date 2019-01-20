package com.example.turbobudzik;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Formatter;
import java.util.List;

public class UserInterface
{
    public static void updateSchedule(List<Lesson> lessons, Context context)
    {
        TextView textView = ((Activity)context).findViewById(R.id.textView);
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        for(Lesson lesson : lessons)
        {
            fm.format("%1$s%n%2$s%n%3$s%n%4$s%n%5$s%n%n",
                    lesson.getSubject(),
                    lesson.getReservationStatus(),
                    lesson.getRoom(),
                    lesson.getFrom(),
                    lesson.getTo());
        }

        String text = fm.toString();

        if(text.length() == 0)
        {
            text = "Brak zajęć";
        }

        textView.setText(text);
    }

    public static String getStudentID(Context context)
    {
        EditText editText = ((Activity)context).findViewById(R.id.studentID);
        return editText.getText().toString();
    }
}
