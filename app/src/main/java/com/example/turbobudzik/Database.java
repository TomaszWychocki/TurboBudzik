package com.example.turbobudzik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "schedule_db";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Lesson.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Lesson.TABLE_NAME);
        onCreate(db);
    }

    public long insertLesson(Lesson lesson)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Lesson.COLUMN_ROOM, lesson.getRoom());
        values.put(Lesson.COLUMN_COURSETYPE, lesson.getCourseType());
        values.put(Lesson.COLUMN_SUBJECT, lesson.getSubject());
        values.put(Lesson.COLUMN_SEMESTER, lesson.getSemester());
        values.put(Lesson.COLUMN_FACULTY, lesson.getFaculty());
        values.put(Lesson.COLUMN_FACULTYABBREVIATION, lesson.getFacultyAbbreviation());
        values.put(Lesson.COLUMN_FIELDOFSTUDY, lesson.getFieldOfStudy());
        values.put(Lesson.COLUMN_RESERVATIONSTATUS, lesson.getReservationStatus());
        values.put(Lesson.COLUMN_RESERVATIONSTATUSABBREVIATION, lesson.getReservationStatusAbbreviation());
        values.put(Lesson.COLUMN_STATUS, lesson.getStatus());
        values.put(Lesson.COLUMN_GROUP, lesson.getGroup());
        values.put(Lesson.COLUMN_FROM, lesson.getFrom());
        values.put(Lesson.COLUMN_TO, lesson.getTo());
        values.put(Lesson.COLUMN_ACADEMICTITLE, lesson.getAcademicTitle());
        values.put(Lesson.COLUMN_NAME, lesson.getName());
        values.put(Lesson.COLUMN_SURNAME, lesson.getSurname());

        long id = db.insert(Lesson.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void clearDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Lesson.TABLE_NAME);
    }

    public List<Lesson> getLessonsBetween(String dateFrom, String dateTo)
    {
        List<Lesson> Lessons = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Lesson.TABLE_NAME
                + " WHERE " + Lesson.COLUMN_FROM + " >= \"" + dateFrom + "\""
                + " AND " + Lesson.COLUMN_FROM + " <= \"" + dateTo + "\""
                + " ORDER BY " + Lesson.COLUMN_FROM + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Lesson Lesson = new Lesson();
                Lesson.setId(cursor.getInt(cursor.getColumnIndex(Lesson.COLUMN_ID)));
                Lesson.setRoom(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_ROOM)));
                Lesson.setCourseType(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_COURSETYPE)));
                Lesson.setSubject(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_SUBJECT)));
                Lesson.setSemester(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_SEMESTER)));
                Lesson.setFaculty(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_FACULTY)));
                Lesson.setFacultyAbbreviation(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_FACULTYABBREVIATION)));
                Lesson.setFieldOfStudy(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_FIELDOFSTUDY)));
                Lesson.setReservationStatus(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_RESERVATIONSTATUS)));
                Lesson.setReservationStatusAbbreviation(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_RESERVATIONSTATUSABBREVIATION)));
                Lesson.setStatus(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_STATUS)));
                Lesson.setGroup(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_GROUP)));
                Lesson.setFrom(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_FROM)));
                Lesson.setTo(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_TO)));
                Lesson.setAcademicTitle(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_ACADEMICTITLE)));
                Lesson.setName(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_NAME)));
                Lesson.setSurname(cursor.getString(cursor.getColumnIndex(Lesson.COLUMN_SURNAME)));

                Lessons.add(Lesson);
            } while (cursor.moveToNext());
        }

        db.close();
        return Lessons;
    }

    public int getLessonsCount()
    {
        String countQuery = "SELECT * FROM " + Lesson.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}