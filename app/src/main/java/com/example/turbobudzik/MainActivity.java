package com.example.turbobudzik;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        showLessons();
    }

    public void sendRequest(View v)
    {
        new HttpRequest(this).execute();
        showLessons();
    }

    public void showLessons()
    {
        String dateFrom = DateTimeUtils.now();
        String dateTo = DateTimeUtils.now(7);

        List<Lesson> lessons = db.getLessonsBetween(dateFrom, dateTo);
        UserInterface.updateSchedule(lessons, this);
    }
}
