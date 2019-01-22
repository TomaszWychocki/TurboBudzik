package com.example.turbobudzik;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HttpRequest extends AsyncTask<String, Void, Boolean>
{
    private Context context;
    private Database db;
    private ProgressDialog progressBar;

    HttpRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);
        progressBar.setMessage("Pobieranie planu zajęć...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
    }

    @Override
    protected Boolean doInBackground(String... urls)
    {
        Boolean result = true;
        db = new Database(context);

        try
        {
            String studentID = UserInterface.getStudentID(context);
            Log.i("stuid", studentID);
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI("http://plan.zut.edu.pl/api/schedule/" + studentID);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);

            int httpStatus = response.getStatusLine().getStatusCode();

            if (httpStatus == 200)
            {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONArray jsonArray = new JSONArray(data);

                db.clearDatabase();

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject day = jsonArray.getJSONObject(i);
                    JSONArray lessons = (JSONArray)day.get("lessons");

                    String date = (String)day.get("date");
                    date = DateTimeUtils.changeDateFormat(date, "dd-MM-yyyy", "yyyy-MM-dd");

                    for(int j = 0; j < lessons.length(); j++)
                    {
                        JSONObject jsonLesson = lessons.getJSONObject(j);
                        JSONObject teacher = (JSONObject)jsonLesson.get("teacher");
                        JSONObject timeRange = (JSONObject)jsonLesson.get("timeRange");

                        Lesson lesson = new Lesson();

                        lesson.setRoom((String)jsonLesson.optString("room"));
                        lesson.setCourseType((String)jsonLesson.optString("courseType"));
                        lesson.setSubject((String)jsonLesson.optString("subject"));
                        lesson.setSemester((String)jsonLesson.optString("semester"));
                        lesson.setFaculty((String)jsonLesson.optString("faculty"));
                        lesson.setFacultyAbbreviation((String)jsonLesson.optString("facultyAbbreviation"));
                        lesson.setFieldOfStudy((String)jsonLesson.optString("fieldOfStudy"));
                        lesson.setReservationStatus((String)jsonLesson.optString("reservationStatus"));
                        lesson.setReservationStatusAbbreviation((String)jsonLesson.optString("reservationStatusAbbreviation"));
                        lesson.setStatus((String)jsonLesson.optString("status"));
                        lesson.setGroup((String)jsonLesson.optString("group"));

                        lesson.setAcademicTitle((String)teacher.optString("academicTitle"));
                        lesson.setName((String)teacher.optString("name"));
                        lesson.setSurname((String)teacher.optString("surname"));

                        lesson.setFrom(date + " " + (String)timeRange.optString("from") + ":00");
                        lesson.setTo(date + " " + (String)timeRange.optString("to") + ":00");

                        db.insertLesson(lesson);
                    }
                }
            }
            else
            {
                result = false;
            }

        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection: "+e.toString());
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    protected void onPostExecute(Boolean result)
    {
        if(result)
        {
            String dateFrom = DateTimeUtils.now();
            String dateTo = DateTimeUtils.now(7);

            List<Lesson> lessons = db.getLessonsBetween(dateFrom, dateTo);
            UserInterface.updateSchedule(lessons, context);
        }
        else
        {
            Toast.makeText(context, "Connection error!", Toast.LENGTH_SHORT).show();
        }

        progressBar.dismiss();
    }
}
