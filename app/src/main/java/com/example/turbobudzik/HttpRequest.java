package com.example.turbobudzik;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

public class HttpRequest extends AsyncTask<String, Void, String>
{
    private Context context;

    HttpRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls)
    {
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = format.format(new Date());

        try
        {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI("http://plan.zut.edu.pl/api/schedule/36225");
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);

            int httpStatus = response.getStatusLine().getStatusCode();

            if (httpStatus == 200)
            {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONArray jsonArray = new JSONArray(data);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject day = jsonArray.getJSONObject(i);
                    JSONArray lessons = (JSONArray)day.get("lessons");
                    String date = (String)day.get("date");

                    for(int j = 0; j < lessons.length(); j++)
                    {
                        JSONObject lesson = lessons.getJSONObject(j);
                        String room = (String)lesson.get("room");
                        String courseType = (String)lesson.get("courseType");
                        String subject = (String)lesson.get("subject");
                        String semester = (String)lesson.get("semester");
                        String faculty = (String)lesson.get("faculty");
                        String facultyAbbreviation = (String)lesson.get("facultyAbbreviation");
                        String fieldOfStudy = (String)lesson.get("fieldOfStudy");
                        String reservationStatus = (String)lesson.get("reservationStatus");
                        String reservationStatusAbbreviation = (String)lesson.get("reservationStatusAbbreviation");
                        String status = (String)lesson.get("status");
                        String group = (String)lesson.get("group");

                        //teacher timeRange

                        if(date.equals(currentDate))
                        {
                            result += date + " " + subject + " " + reservationStatus + " " + room + '\n' + '\n';
                        }

                        Log.i("json_result", date + " " + subject + " " + reservationStatusAbbreviation);
                    }
                }

                if(result.length() == 0)
                {
                    result = "Brak zajęć";
                }
            }
        } catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection: "+e.toString());
        }

        return result;
    }

    protected void onPostExecute(String result)
    {
        TextView textView = (TextView)((Activity)this.context).findViewById(R.id.textView);
        textView.setText(result);
    }
}
