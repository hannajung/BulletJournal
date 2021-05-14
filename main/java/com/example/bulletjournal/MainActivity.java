package com.example.bulletjournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView myDate;
    Button saveDate;
    int t_year;
    int t_month;
    int t_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*0.6));

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        saveDate = (Button) findViewById(R.id.saveDate);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                t_year = year;
                t_month = month;
                t_day = dayOfMonth;
            }
        });

        saveDate.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intent = new Intent(getApplicationContext(), DayActivity.class);
                // when a user did not select a date but just clicked save button
                // let the yyyy/mm/dd to be today's date
                if (t_year == 0){
                    t_year = Calendar.getInstance().get(Calendar.YEAR);
                    t_month = Calendar.getInstance().get(Calendar.MONTH);
                    t_day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                }

                intent.putExtra("YEAR", t_year);
                intent.putExtra("MONTH",t_month+1);
                intent.putExtra("DAY",t_day);
                // 기차 출발
                startActivity(intent);
            }
        });

        }


}